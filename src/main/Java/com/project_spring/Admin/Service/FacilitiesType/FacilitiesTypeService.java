package com.project_spring.Admin.Service.FacilitiesType;

import com.project_spring.Admin.DAO.FacilitiesType.FacilitiesTypeDao;
import com.project_spring.Admin.Model.FacilitiesType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitiesTypeService implements IFacilitiesTypeService{
    @Autowired
    FacilitiesTypeDao facilitiesTypeDao;

    @Override
    public boolean addFacilitiesType(FacilitiesType facilitiesType) {
        return facilitiesTypeDao.addFacilitiesType(facilitiesType);
    }

    @Override
    public boolean deleteFacilitiesType(String facilitiesTypeId) {
        return facilitiesTypeDao.deleteFacilitiesType(facilitiesTypeId);
    }

    @Override
    public boolean updateFacilitiesType(FacilitiesType facilitiesType) {
        return facilitiesTypeDao.updateFacilitiesType(facilitiesType);
    }

    @Override
    public boolean isExistFacilitiesType(FacilitiesType facilitiesType) {
        return facilitiesTypeDao.isExistFacilitiesType(facilitiesType);
    }

    @Override
    public List<FacilitiesType> displayAllFacilitiesType() {
        return facilitiesTypeDao.displayAllFacilitiesType();
    }

    @Override
    public FacilitiesType findFacilitiesTypeById(String facilitiesTypeId) {
        return facilitiesTypeDao.findFacilitiesTypeById(facilitiesTypeId);
    }
}
