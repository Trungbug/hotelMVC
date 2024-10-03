package com.project_spring.Admin.Service.ProductType;

import com.project_spring.Admin.DAO.ProductType.ProductTypeDao;
import com.project_spring.Admin.Model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService implements IProductTypeService{
    @Autowired
    ProductTypeDao productTypeDao;

    @Override
    public boolean addProductType(ProductType productType) {
        if(!productTypeDao.isExistProdcutType(productType)) {
            return productTypeDao.addProductType(productType);
        }
        return false;
    }

    @Override
    public boolean deleteProductType(int id) {
        return productTypeDao.deleteProductType(id);
    }

    @Override
    public boolean updateProductType(ProductType productType) {
        return !productTypeDao.isExistProdcutType(productType) ? productTypeDao.updateProductType(productType) : false;
    }

    @Override
    public boolean isExistProdcutType(ProductType productType) {
        return productTypeDao.isExistProdcutType(productType);
    }

    @Override
    public List<ProductType> listAllProductType() {
        return productTypeDao.listAllProductType();
    }

    @Override
    public ProductType findProductTypeById(int id) {
        return productTypeDao.findProductTypeById(id);
    }
}
