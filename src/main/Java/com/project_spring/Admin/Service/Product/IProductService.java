package com.project_spring.Admin.Service.Product;

import com.project_spring.Admin.Model.Product;

import java.util.List;

public interface IProductService {
    boolean addProduct(Product product);

    boolean deleteProduct(int id);

    boolean updateProduct(Product product);

    boolean isExistProdcut(Product product);

    List<Product> listAllProduct();

    Product findProductById(int id);
}
