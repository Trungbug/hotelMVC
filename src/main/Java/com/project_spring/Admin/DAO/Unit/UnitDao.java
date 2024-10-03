package com.project_spring.Admin.DAO.Unit;

import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Model.Unit;
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
public class UnitDao implements IUnitDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean addUnit(Unit unit){
        try {
            String query = "INSERT INTO Unit(name, description) VALUE(?, ?)";
            jdbcTemplate.update(query, unit.getName(), unit.getDescription());
            int id = getId();
            if(id != -1) {
                unit.setUnitId(id + 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public int getId() {
        int maxId = -1;
        try {
            String query = "SELECT MAX(unit_id) FROM Unit";
            maxId = jdbcTemplate.queryForObject(query, Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return maxId;
    }

    @Override
    public boolean deleteUnit(int id) {
        try {
            String query = "DELETE FROM Unit WHERE unit_id = ?";
            jdbcTemplate.update(query, id);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUnit(Unit unit) {
        try {
            String query = "UPDATE Unit SET name = ?, description = ? WHERE unit_id = ?";
            jdbcTemplate.update(query, unit.getName(), unit.getDescription(), unit.getUnitId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean isExistUnit(Unit unit) {
        List<Unit> units = new ArrayList<>();
        try {
            String query = "SELECT * FROM Unit WHERE name = ?";
            units = jdbcTemplate.query(query, new Object[]{unit.getName()}, new RowMapper<Unit>() {
                @Override
                public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Unit unit = new Unit();
                    unit.setUnitId(rs.getInt("unit_id"));
                    unit.setName(rs.getString("name"));
                    unit.setDescription(rs.getString("description"));
                    return unit;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return units.size() > 0;
    }

    @Override
    public List<Unit> listAllUnit() {
        List<Unit> units = new ArrayList<>();
        try {
            String query = "SELECT * FROM Unit";
            units = jdbcTemplate.query(query, new Object[]{}, new RowMapper<Unit>() {
                @Override
                public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Unit unit = new Unit();
                    unit.setUnitId(rs.getInt("unit_id"));
                    unit.setName(rs.getString("name"));
                    unit.setDescription(rs.getString("description"));
                    return unit;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return units;
    }

    @Override
    public Unit findUnitById(int id) {
        Unit unit = new Unit();
        try {
            String query = "SELECT * FROM Unit WHERE unit_id = ?";
//        return jdbcTemplate.queryForObject(query, new Object[] {id}, new RowMapper<RoomType>() {
//            @Override
//            public RoomType mapRow(ResultSet rs, int rowNum) throws SQLException {
//                RoomType roomType = new RoomType();
//                roomType.setRoomTypeId(rs.getInt("RoomTypeId"));
//                roomType.setTypeName(rs.getString("TypeName"));
//                roomType.setDescription(rs.getString("Description"));
//                return roomType;
//            }
//        });
            unit = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Unit.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return unit;
    }
}
