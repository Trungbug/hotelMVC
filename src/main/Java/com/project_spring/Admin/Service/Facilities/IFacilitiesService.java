package com.project_spring.Admin.Service.Facilities;

import com.project_spring.Admin.Model.Facilities;

import java.util.List;

public interface IFacilitiesService {
    boolean addFacilities(Facilities facilities);

    boolean deleteFacilities(int facilitiesId);

    boolean updateFacilities(Facilities facilities);

    boolean isExistFacilities(Facilities facilities);
    List<Facilities> displayAllFacilities();
    public boolean isExistFacilitiesByFacilitiesTypeId(String facilitiesTypeId);
    Facilities findFacilitiesById(int facilitiesId);
}
