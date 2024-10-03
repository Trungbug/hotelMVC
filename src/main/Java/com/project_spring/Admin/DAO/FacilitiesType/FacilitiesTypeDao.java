package com.project_spring.Admin.DAO.FacilitiesType;

import com.project_spring.Admin.Model.FacilitiesType;
import com.project_spring.Admin.Service.Facilities.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FacilitiesTypeDao implements IFacilitiesTypeDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FacilitiesService facilitiesService;

    @Override
    public boolean addFacilitiesType(FacilitiesType facilitiesType) {
        String query = "INSERT INTO FacilitiesType(id, facilitiesTypeName) VALUE(?, ?)";
        return jdbcTemplate.update(query, facilitiesType.getId(),facilitiesType.getFacilitiesTypeName()) > 0;
    }

    @Override
    public boolean deleteFacilitiesType(String facilitiesTypeId) {
        if(facilitiesService.isExistFacilitiesByFacilitiesTypeId(facilitiesTypeId))
            return false;

        String query = "DELETE FROM FacilitiesType WHERE id = ?";
        return jdbcTemplate.update(query, facilitiesTypeId) > 0;
    }

    @Override
    public boolean updateFacilitiesType(FacilitiesType facilitiesType) {
        String query = "UPDATE FacilitiesType SET facilitiesTypeName = ? WHERE id = ?";
        return jdbcTemplate.update(query, facilitiesType.getFacilitiesTypeName(), facilitiesType.getId()) > 0;
    }

    @Override
    public boolean isExistFacilitiesType(FacilitiesType facilitiesType) {
        return false;
    }

    @Override
    public List<FacilitiesType> displayAllFacilitiesType() {
        String query = "SELECT * FROM facilitiestype";
        List<FacilitiesType> facilitiesTypes = jdbcTemplate.query(query, new Object[]{}, new RowMapper<FacilitiesType>() {
            @Override
            public FacilitiesType mapRow(ResultSet rs, int rowNum) throws SQLException {
                FacilitiesType facilitiesType = new FacilitiesType();
                facilitiesType.setId(rs.getString("id"));
                facilitiesType.setFacilitiesTypeName(rs.getString("facilitiesTypeName"));
                return facilitiesType;
            }
        });
        return facilitiesTypes;
    }

    @Override
    public FacilitiesType findFacilitiesTypeById(String facilitiesTypeId) {
        String query = "SELECT * FROM FacilitiesType WHERE id = ?";
        FacilitiesType facilitiesType = jdbcTemplate.queryForObject(query,new Object[] {facilitiesTypeId}, new BeanPropertyRowMapper<>(FacilitiesType.class));
        return facilitiesType;
    }
}