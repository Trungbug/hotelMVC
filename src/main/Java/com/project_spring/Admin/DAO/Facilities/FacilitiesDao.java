package com.project_spring.Admin.DAO.Facilities;

import com.project_spring.Admin.Model.Facilities;
import com.project_spring.Admin.Model.FacilitiesType;
import com.project_spring.Admin.Service.FacilitiesType.FacilitiesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FacilitiesDao implements IFacilitiesDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FacilitiesTypeService facilitiesTypeService;

    @Override
    public boolean addFacilities(Facilities facilities) {
        String query = "INSERT INTO Facilities(id, name, price, quantity, TotalPrice, date_buy, status, manufacturer, facilitiestypeid) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, facilities.getId(),facilities.getName(), facilities.getPrice(), facilities.getQuantity(), facilities.getTotalPrice(), facilities.getDate_buy(), facilities.getStatus(), facilities.getManufacturer(), facilities.getFacilitiesType().getId()) > 0;
    }

    @Override
    public boolean deleteFacilities(int facilitiesId) {
        String query = "DELETE FROM Facilities WHERE id = ?";
        return jdbcTemplate.update(query, facilitiesId) > 0;
    }

    @Override
    public boolean updateFacilities(Facilities facilities) {
        String query = "UPDATE Facilities SET name = ?, price = ?, quantity = ?, TotalPrice = ? ,date_buy = ?, status = ?, manufacturer = ?, facilitiestypeid = ? WHERE id = ?";
        return jdbcTemplate.update(query, facilities.getName(), facilities.getPrice(), facilities.getQuantity(), facilities.getQuantity() * facilities.getPrice(), facilities.getDate_buy(), facilities.getStatus(), facilities.getManufacturer(), facilities.getFacilitiesType().getId(), facilities.getId()) > 0;
    }

    @Override
    public boolean isExistFacilities(Facilities facilities) {
        return false;
    }
    public boolean checkExists(int id) {
        String query = "SELECT COUNT(*) FROM Facilities WHERE id = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, id);
        return count > 0;
    }

    @Override
    public List<Facilities> displayAllFacilities() {
        String query = "SELECT * FROM Facilities";
        List<Facilities> facilities = jdbcTemplate.query(query, new Object[]{}, new RowMapper<Facilities>() {
            @Override
            public Facilities mapRow(ResultSet rs, int rowNum) throws SQLException {
                Facilities facilitie = new Facilities();
                facilitie.setId(rs.getInt("id"));
                facilitie.setName(rs.getString("name"));
                facilitie.setPrice(rs.getDouble("price"));
                facilitie.setQuantity(rs.getInt("quantity"));
                facilitie.setTotalPrice();
                facilitie.setDate_buy(rs.getDate("date_buy"));
                facilitie.setStatus(rs.getString("status"));
                facilitie.setManufacturer(rs.getString("manufacturer"));
                facilitie.setFacilitiesTypeId(rs.getString("facilitiestypeid"));
                FacilitiesType facilitiesType = facilitiesTypeService.findFacilitiesTypeById(rs.getString("facilitiestypeid"));
                facilitie.setFacilitiesType(facilitiesType);
                return facilitie;
            }
        });
        return facilities;
    }

    public boolean isExistFacilitiesByFacilitiesTypeId(String facilitiesTypeId) {
        String query = "SELECT * FROM Facilities WHERE facilitiestypeid = ?";
        return jdbcTemplate.query(query, new Object[] {facilitiesTypeId}, new RowMapper<Facilities>() {
            @Override
            public Facilities mapRow(ResultSet rs, int rowNum) throws SQLException {
                Facilities facilitie = new Facilities();
                facilitie.setId(rs.getInt("id"));
                facilitie.setName(rs.getString("name"));
                facilitie.setPrice(rs.getDouble("price"));
                facilitie.setQuantity(rs.getInt("quantity"));
                facilitie.setTotalPrice();
                facilitie.setDate_buy(rs.getDate("date_buy"));
                facilitie.setStatus(rs.getString("status"));
                facilitie.setManufacturer(rs.getString("manufacturer"));
                facilitie.setFacilitiesTypeId(rs.getString("facilitiestypeid"));
                FacilitiesType facilitiesType = facilitiesTypeService.findFacilitiesTypeById(rs.getString("facilitiestypeid"));
                facilitie.setFacilitiesType(facilitiesType);
                return facilitie;
            }
        }).size() > 0;
    }

    @Override
    public Facilities findFacilitiesById(int facilitiesId) {
        String query = "SELECT * FROM Facilities WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[] {facilitiesId}, new RowMapper<Facilities>() {
            @Override
            public Facilities mapRow(ResultSet rs, int rowNum) throws SQLException {
                Facilities facilitie = new Facilities();
                facilitie.setId(rs.getInt("id"));
                facilitie.setName(rs.getString("name"));
                facilitie.setPrice(rs.getDouble("price"));
                facilitie.setQuantity(rs.getInt("quantity"));
                facilitie.setTotalPrice();
                facilitie.setDate_buy(rs.getDate("date_buy"));
                facilitie.setStatus(rs.getString("status"));
                facilitie.setManufacturer(rs.getString("manufacturer"));
                facilitie.setFacilitiesTypeId(rs.getString("facilitiestypeid"));
                FacilitiesType facilitiesType = facilitiesTypeService.findFacilitiesTypeById(rs.getString("facilitiestypeid"));
                facilitie.setFacilitiesType(facilitiesType);
                return facilitie;
            }
        });
    }
}