package com.project_spring.Admin.DAO.Facilities;

import com.project_spring.Admin.Model.Facilities;
import com.project_spring.Admin.Model.FacilitiesType;

import java.util.List;

public interface IFacilitiesDao {
    boolean addFacilities(Facilities facilities);

    boolean deleteFacilities(int facilitiesId);

    boolean updateFacilities(Facilities facilities);

    boolean isExistFacilities(Facilities facilities);
    List<Facilities> displayAllFacilities();
    public boolean isExistFacilitiesByFacilitiesTypeId(String facilitiesTypeId);
    Facilities findFacilitiesById(int facilitiesId);
}