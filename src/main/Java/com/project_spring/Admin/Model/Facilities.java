package com.project_spring.Admin.Model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.sql.Date;

public class Facilities {
    private int id;

    private String name;

    private double price;

    private int quantity;
    private double totalPrice;

    private Date date_buy;
    private String status;

    private String manufacturer;
    private String facilitiesTypeId;
    private FacilitiesType facilitiesType;


    public Facilities() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = this.quantity * this.price;
    }

    public Date getDate_buy() {
        return date_buy;
    }

    public void setDate_buy(Date date_buy) {
        this.date_buy = date_buy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public FacilitiesType getFacilitiesType() {
        return facilitiesType;
    }

    public void setFacilitiesType(FacilitiesType facilitiesType) {
        this.facilitiesType = facilitiesType;
    }

    public String getFacilitiesTypeId() {
        return facilitiesTypeId;
    }

    public void setFacilitiesTypeId(String facilitiesTypeId) {
        this.facilitiesTypeId = facilitiesTypeId;
    }
}