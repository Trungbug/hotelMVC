package com.project_spring.Admin.Service.Facilities;

import com.project_spring.Admin.DAO.Facilities.FacilitiesDao;
import com.project_spring.Admin.Model.Facilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacilitiesService implements IFacilitiesService {
    @Autowired
    FacilitiesDao facilitiesDao;

    @Override
    public boolean addFacilities(Facilities facilities) {
        return facilitiesDao.addFacilities(facilities);
    }

    @Override
    public boolean deleteFacilities(int facilitiesId) {
        return facilitiesDao.deleteFacilities(facilitiesId);
    }

    @Override
    public boolean updateFacilities(Facilities facilities) {
        return facilitiesDao.updateFacilities(facilities);
    }

    @Override
    public boolean isExistFacilities(Facilities facilities) {
        return facilitiesDao.isExistFacilities(facilities);
    }

    @Override
    public List<Facilities> displayAllFacilities() {
        return facilitiesDao.displayAllFacilities();
    }

    @Override
    public boolean isExistFacilitiesByFacilitiesTypeId(String facilitiesTypeId) {
        return facilitiesDao.isExistFacilitiesByFacilitiesTypeId(facilitiesTypeId);
    }

    @Override
    public Facilities findFacilitiesById(int facilitiesId) {
        return facilitiesDao.findFacilitiesById(facilitiesId);
    }
}