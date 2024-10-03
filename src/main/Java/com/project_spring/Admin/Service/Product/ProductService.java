package com.project_spring.Admin.Service.Product;

import com.project_spring.Admin.DAO.Product.ProductDao;
import com.project_spring.Admin.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    ProductDao productDao;


    @Override
    public boolean addProduct(Product product) {
        if(!productDao.isExistProdcut(product)) {
            return productDao.addProduct(product);
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        return productDao.deleteProduct(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        return !productDao.isExistProdcut(product) ? productDao.updateProduct(product) : false;
    }

    @Override
    public boolean isExistProdcut(Product product) {
        return productDao.isExistProdcut(product);
    }

    @Override
    public List<Product> listAllProduct() {
        return productDao.listAllProduct();
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findProductById(id);
    }
}
