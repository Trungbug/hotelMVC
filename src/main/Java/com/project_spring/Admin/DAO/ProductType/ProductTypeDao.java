package com.project_spring.Admin.DAO.ProductType;

import com.project_spring.Admin.Model.ProductType;
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
public class ProductTypeDao implements IProductTypeDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean addProductType(ProductType productType) {
        try {
            String query = "INSERT INTO ProductType(name, description) VALUES(?, ?)";
            jdbcTemplate.update(query, productType.getName(), productType.getDescription());
            int id = getId();
            if(id != -1) {
                productType.setProductTypeId(id + 1);
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
            String query = "SELECT MAX(ProductTypeId) FROM ProductType";
            maxId = jdbcTemplate.queryForObject(query, Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return maxId;
    }

    @Override
    public boolean deleteProductType(int id) {
        try {
            String query = "DELETE FROM ProductType WHERE ProductTypeId = ?";
            jdbcTemplate.update(query, id);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProductType(ProductType productType) {
        try {
            String query = "UPDATE ProductType SET name = ?, description = ? WHERE ProductTypeId = ?";
            jdbcTemplate.update(query, productType.getName(), productType.getDescription(), productType.getProductTypeId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean isExistProdcutType(ProductType productType) {
        List<ProductType> productTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM ProductType WHERE name = ?";
            productTypes = jdbcTemplate.query(query, new Object[]{productType.getName()}, new RowMapper<ProductType>() {
                @Override
                public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ProductType productType = new ProductType();
                    productType.setProductTypeId(rs.getInt("ProductTypeId"));
                    productType.setName(rs.getString("name"));
                    productType.setDescription(rs.getString("description"));
                    return productType;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productTypes.size() > 0;
    }

    @Override
    public List<ProductType> listAllProductType() {
        List<ProductType> productTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM ProductType";
            productTypes = jdbcTemplate.query(query, new Object[]{}, new RowMapper<ProductType>() {
                @Override
                public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ProductType productType = new ProductType();
                    productType.setProductTypeId(rs.getInt("ProductTypeId"));
                    productType.setName(rs.getString("name"));
                    productType.setDescription(rs.getString("description"));
                    return productType;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productTypes;
    }

    @Override
    public ProductType findProductTypeById(int id) {
        ProductType productType = new ProductType();
        try {
            String query = "SELECT * FROM ProductType WHERE ProductTypeId = ?";
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
            productType = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(ProductType.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productType;
    }
}
