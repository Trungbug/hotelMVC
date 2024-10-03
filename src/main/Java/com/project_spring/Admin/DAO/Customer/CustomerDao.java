package com.project_spring.Admin.DAO.Customer;

import com.project_spring.Admin.Model.Customer;
import com.project_spring.Admin.Model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class CustomerDao implements ICustomerDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int getCurrentId() {
        String query = "SELECT MAX(CustomerId) as customerid FROM Customer";
        Integer maxCustomerId = jdbcTemplate.queryForObject(query, Integer.class);
        return (maxCustomerId != null) ? maxCustomerId : 0;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO CUSTOMER(Name, Email, phoneNumber, gender) VALUE(?, ?, ?, ?)";
        return jdbcTemplate.update(query, customer.getName(), customer.getEmail(), customer.getPhoneNumber(), customer.getGender()) > 0;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        String query = "DELETE FROM CUSTOMER WHERE CustomerId = ?";
        return jdbcTemplate.update(query, customerId) > 0;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE CUSTOMER SET Name = ?, Email = ?, phoneNumber = ?, gender = ? WHERE CustomerId = ?";
        return jdbcTemplate.update(query, customer.getName(), customer.getEmail(), customer.getPhoneNumber(), customer.getGender(), customer.getCustomerId()) > 0;
    }

    @Override
    public boolean isExistCustomer(Customer customer) {
        return false;
    }

    @Override
    public List<Customer> displayAllCustomer() {
        String query = "SELECT * FROM Customer";
        List<Customer> customers = jdbcTemplate.query(query, new Object[]{}, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerId"));
                customer.setName(rs.getString("Name"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setGender(rs.getString("gender"));
                return customer;
            }
        });
        return customers;
    }

    @Override
    public Customer findCustomerById(int id) {
        String query = "SELECT * FROM Customer WHERE customerId = ?";
        Customer customer = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class));
        return customer;
    }

}
