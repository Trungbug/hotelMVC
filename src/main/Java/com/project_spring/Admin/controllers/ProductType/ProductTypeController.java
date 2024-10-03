package com.project_spring.Admin.controllers.ProductType;

import com.project_spring.Admin.Model.ProductType;
import com.project_spring.Admin.Model.Unit;
import com.project_spring.Admin.Service.ProductType.ProductTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;

    @GetMapping(value = "/list-product-type")
    public @ResponseBody List<ProductType> listAllProductTypeAPI() {
        List<ProductType> productTypes = productTypeService.listAllProductType();
        return productTypes;
    }

    @GetMapping(value = "/find-product-type/productTypeId={productTypeId}")
    public @ResponseBody ProductType findProductTypeAPI(@PathVariable("productTypeId") int productTypeId) {
        ProductType productType = productTypeService.findProductTypeById(productTypeId);
        return productType;
    }

    @GetMapping(value = "/danh-sach-loai-san-pham")
    public String listAllUnit() {
        return "Admin/ProductType/list-product-type";
    }

    @DeleteMapping(value = "/xoa-loai-san-pham/productTypeId={productTypeId}")
    public @ResponseBody ProductType deleteProductTypeAPI(@PathVariable("productTypeId") int productTypeId) {
        ProductType productType = productTypeService.findProductTypeById(productTypeId);
        productTypeService.deleteProductType(productTypeId);
        productType.setProductTypeId(productTypeId);
        return productType;
    }

    @GetMapping(value = "/them-loai-san-pham")
    public String addProductType() {
        return "Admin/ProductType/add-product-type";
    }

    @PostMapping(value = "/add-product-type")
    public @ResponseBody ProductType addProductTypeAPI(@RequestBody ProductType productType) {
        productTypeService.addProductType(productType);
        return productType;
    }

    @GetMapping(value = "/sua-loai-san-pham/productTypeId={productTypeId}")
    public String editProductType(HttpServletRequest httpServletRequest, @PathVariable("productTypeId") int productTypeId) {
        ProductType productType = productTypeService.findProductTypeById(productTypeId);
        httpServletRequest.setAttribute("productType", productType);
        return "Admin/ProductType/edit-product-type";
    }

    @PutMapping(value = "/edit-product-type/productTypeId={productTypeId}")
    public @ResponseBody ProductType editProductTypeAPI(@PathVariable("productTypeId") int productTypeId, @RequestBody ProductType productType) {
        productType.setProductTypeId(productTypeId);
        productTypeService.updateProductType(productType);
        return productType;
    }
}
