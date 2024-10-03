package com.project_spring.Admin.DAO.RoomType;

import Config.config.JPAConfig;
import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Repository.RoomTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.jpa.JpaTemplate;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomTypeDao implements IRoomTypeDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public boolean addRoomType(RoomType roomType){
        try {
            String query = "INSERT INTO RoomType(TypeName, Description) VALUE(?, ?)";
            jdbcTemplate.update(query, roomType.getTypeName(), roomType.getDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteRoomType(int roomTypeId) {
        try {
            String query = "DELETE FROM RoomType WHERE RoomTypeId = ?";
            jdbcTemplate.update(query, roomTypeId);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRoomType(RoomType roomType) {
        try {
            String query = "UPDATE RoomType SET TypeName = ?, Description = ? WHERE RoomTypeId = ?";
            jdbcTemplate.update(query, roomType.getTypeName(), roomType.getDescription(), roomType.getRoomTypeId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean isExistRoomType(RoomType roomType) {
        return false;
    }

    @Override
    public List<RoomType> displayAllRoomType() {
        List<RoomType> roomTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM roomtype";
             roomTypes = jdbcTemplate.query(query, new Object[]{}, new RowMapper<RoomType>() {
                @Override
                public RoomType mapRow(ResultSet rs, int rowNum) throws SQLException {
                    RoomType roomType = new RoomType();
                    roomType.setRoomTypeId(rs.getInt("RoomTypeId"));
                    roomType.setTypeName(rs.getString("TypeName"));
                    roomType.setDescription(rs.getString("Description"));
                    return roomType;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return roomTypes;
    }

    @Override
    public RoomType findRoomTypeById(int id) {
        RoomType roomType = new RoomType();
        try {
            String query = "SELECT * FROM roomtype WHERE RoomTypeId = ?";
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
            roomType = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(RoomType.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return roomType;
    }
}