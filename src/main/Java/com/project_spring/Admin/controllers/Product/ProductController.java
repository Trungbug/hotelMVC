package com.project_spring.Admin.controllers.Product;

import com.project_spring.Admin.Model.Product;
import com.project_spring.Admin.Model.ProductType;
import com.project_spring.Admin.Model.Unit;
import com.project_spring.Admin.Service.Product.ProductService;
import com.project_spring.Admin.Service.ProductType.ProductTypeService;
import com.project_spring.Admin.Service.Unit.UnitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    UnitService unitService;

    @GetMapping(value = "/list-product")
    public @ResponseBody List<Product> listAllProductTypeAPI() {
        List<Product> products = productService.listAllProduct();
        return products;
    }

    @GetMapping(value = "/danh-sach-san-pham")
    public String listAllUnit() {
        return "Admin/Product/list-product";
    }

    @DeleteMapping(value = "/xoa-san-pham/productId={productId}")
    public @ResponseBody Product deleteProductAPI(@PathVariable("productId") int productId) {
        Product product = productService.findProductById(productId);
        product.setProductId(productId);
        productService.deleteProduct(productId);
        return product;
    }

    @GetMapping(value = "/them-san-pham")
    public String addProduct(HttpServletRequest httpServletRequest) {
        List<ProductType> productTypes =  productTypeService.listAllProductType();
        httpServletRequest.setAttribute("productTypes", productTypes);
        List<Unit> units = unitService.listAllUnit();
        httpServletRequest.setAttribute("units", units);
        return "Admin/Product/add-product";
    }

    @PostMapping(value = "/add-product")
    public @ResponseBody Product addProductAPI(@RequestBody Product product) {
        productService.addProduct(product);
        return product;
    }

    @GetMapping(value = "/sua-san-pham/productId={productId}")
    public String editProduct(HttpServletRequest httpServletRequest, @PathVariable("productId") int productId) {
        Product product = productService.findProductById(productId);
        httpServletRequest.setAttribute("product", product);

        List<ProductType> productTypes =  productTypeService.listAllProductType();
        httpServletRequest.setAttribute("productTypes", productTypes);

        List<Unit> units = unitService.listAllUnit();
        httpServletRequest.setAttribute("units", units);

        return "Admin/Product/edit-product";
    }

    @PutMapping(value = "/edit-product/productId={productId}")
    public @ResponseBody Product editProductAPI(@PathVariable("productId") int productId, @RequestBody Product product) {
        product.setProductId(productId);
        productService.updateProduct(product);
        return product;
    }
}