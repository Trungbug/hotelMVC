package com.project_spring.Admin.DAO.Unit;

import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Model.Unit;

import java.util.List;

public interface IUnitDao {
    boolean addUnit(Unit unit);

    boolean deleteUnit(int id);

    boolean updateUnit(Unit unit);

    boolean isExistUnit(Unit unit);
    List<Unit> listAllUnit();
    Unit findUnitById(int id);
}
