package com.project_spring.Admin.Service.Unit;

import com.project_spring.Admin.DAO.Unit.UnitDao;
import com.project_spring.Admin.Model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService implements IUnitService {
    @Autowired
    UnitDao unitDao;

    @Override
    public boolean addUnit(Unit unit){
        if(!unitDao.isExistUnit(unit)) {
            return unitDao.addUnit(unit);
        }
        return false;
    }

    @Override
    public boolean deleteUnit(int id) {
        return unitDao.deleteUnit(id);
    }

    @Override
    public boolean updateUnit(Unit unit) {
        return !unitDao.isExistUnit(unit) ? unitDao.updateUnit(unit) : false;
    }

    @Override
    public boolean isExistUnit(Unit unit) {
        return unitDao.isExistUnit(unit);
    }

    @Override
    public List<Unit> listAllUnit() {
        return unitDao.listAllUnit();
    }

    @Override
    public Unit findUnitById(int id) {
        return unitDao.findUnitById(id);
    }
}
