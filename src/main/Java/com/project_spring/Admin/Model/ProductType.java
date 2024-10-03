package com.project_spring.Admin.Model;

public class ProductType {
    private int productTypeId;
    private String name;
    private String description;

    public ProductType() {
    }

    public ProductType(int productTypeId, String name, String description) {
        this.productTypeId = productTypeId;
        this.name = name;
        this.description = description;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}