package com.project_spring.Admin.DAO.Product;

import com.project_spring.Admin.DAO.ProductType.ProductTypeDao;
import com.project_spring.Admin.DAO.Unit.UnitDao;
import com.project_spring.Admin.Model.ProductType;
import com.project_spring.Admin.Model.Product;
import com.project_spring.Admin.Model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao implements IProductDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UnitDao unitDao;

    @Autowired
    ProductTypeDao productTypeDao;


    @Override
    public boolean addProduct(Product product) {
        try {
            String query = "INSERT INTO Product(name, productTypeId, unit_id, date_add, expired, price, capital_price, status, description) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, product.getName(), product.getProducType().getProductTypeId(), product.getUnit().getUnitId(),
                    product.getDateAdd(), product.getExpired(), product.getPrice(), product.getCapitalPrice(), product.getStatus(), product.getDescription());
            int id = getId();
            if(id != -1) {
                product.setProductId(id + 1);
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
            String query = "SELECT MAX(product_id) FROM Product";
            maxId = jdbcTemplate.queryForObject(query, Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return maxId;
    }

    @Override
    public boolean deleteProduct(int id) {
        try {
            String query = "DELETE FROM Product WHERE product_id = ?";
            jdbcTemplate.update(query, id);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            String query = "UPDATE Product SET name = ?, productTypeId = ?, unit_id = ?, date_add = ?, expired = ?, price = ?,  capital_price = ?, status = ?, description = ? WHERE product_id = ?";
            jdbcTemplate.update(query, product.getName(), product.getProducType().getProductTypeId(), product.getUnit().getUnitId(),
                    product.getDateAdd(), product.getExpired(), product.getPrice(), product.getCapitalPrice(), product.getStatus(), product.getDescription(), product.getProductId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean isExistProdcut(Product product) {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product WHERE name = ?";
            products = jdbcTemplate.query(query, new Object[]{product.getName()}, new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setDateAdd(rs.getDate("date_add"));
                    product.setExpired(rs.getDate("expired"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCapitalPrice(rs.getDouble("capital_price"));
                    product.setDescription(rs.getString("description"));
                    product.setStatus(rs.getString("status"));

                    Unit unit = unitDao.findUnitById(rs.getInt("unit_id"));
                    product.setUnit(unit);

                    ProductType productType = productTypeDao.findProductTypeById(rs.getInt("productTypeId"));
                    product.setProducType(productType);
                    return product;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products.size() > 0;
    }

    @Override
    public List<Product> listAllProduct() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product";
            products = jdbcTemplate.query(query, new Object[]{}, new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setDateAdd(rs.getDate("date_add"));
                    product.setExpired(rs.getDate("expired"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCapitalPrice(rs.getDouble("capital_price"));
                    product.setDescription(rs.getString("description"));
                    product.setStatus(rs.getString("status"));

                    Unit unit = unitDao.findUnitById(rs.getInt("unit_id"));
                    product.setUnit(unit);

                    ProductType productType = productTypeDao.findProductTypeById(rs.getInt("productTypeId"));
                    product.setProducType(productType);
                    return product;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public Product findProductById(int id) {
        Product product = new Product();
        try {
            String query = "SELECT * FROM Product WHERE product_id = ?";
            return jdbcTemplate.queryForObject(query, new Object[] {id}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDateAdd(rs.getDate("date_add"));
                product.setExpired(rs.getDate("expired"));
                product.setPrice(rs.getDouble("price"));
                product.setCapitalPrice(rs.getDouble("capital_price"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getString("status"));

                Unit unit = unitDao.findUnitById(rs.getInt("unit_id"));
                product.setUnit(unit);

                ProductType productType = productTypeDao.findProductTypeById(rs.getInt("productTypeId"));
                product.setProducType(productType);
                return product;
            }
        });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
}
