package com.project_spring.Admin.Model;

import jakarta.validation.constraints.NotEmpty;

public class FacilitiesType {

    @NotEmpty
    private String id;

    @NotEmpty
    private String facilitiesTypeName;

    public FacilitiesType() {
    }

    public FacilitiesType(String id, String facilitiesTypeName) {
        this.id = id;
        this.facilitiesTypeName = facilitiesTypeName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFacilitiesTypeName() {
        return facilitiesTypeName;
    }

    public void setFacilitiesTypeName(String facilitiesTypeName) {
        this.facilitiesTypeName = facilitiesTypeName;
    }
}