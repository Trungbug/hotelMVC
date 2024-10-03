package com.project_spring.Admin.Model;

import java.sql.Date;

public class Product {
    private int productId;
    String name;
    private ProductType productType;
    private Unit unit;
    private Date dateAdd;
    private Date expired;
    private double price;
    private double capitalPrice;
    private String status;
    private String description;

    public Product() {
    }

    public Product(int productId, String name, ProductType productType, Unit unit, Date dateAdd, Date expired, double price, double capitalPrice, String status, String description) {
        this.productId = productId;
        this.name = name;
        this.productType = productType;
        this.unit = unit;
        this.dateAdd = dateAdd;
        this.expired = expired;
        this.price = price;
        this.capitalPrice = capitalPrice;
        this.status = status;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductType getProducType() {
        return productType;
    }

    public void setProducType(ProductType productType) {
        this.productType = productType;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCapitalPrice() {
        return capitalPrice;
    }

    public void setCapitalPrice(double capitalPrice) {
        this.capitalPrice = capitalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}