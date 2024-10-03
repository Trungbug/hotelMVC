package com.project_spring.Admin.DAO.FacilitiesType;

import com.project_spring.Admin.Model.FacilitiesType;

import java.util.List;

public interface IFacilitiesTypeDao {
    boolean addFacilitiesType(FacilitiesType facilitiesType);

    boolean deleteFacilitiesType(String facilitiesTypeId);

    boolean updateFacilitiesType(FacilitiesType facilitiesType);

    boolean isExistFacilitiesType(FacilitiesType facilitiesType);
    List<FacilitiesType> displayAllFacilitiesType();
    FacilitiesType findFacilitiesTypeById(String facilitiesTypeId);
}
