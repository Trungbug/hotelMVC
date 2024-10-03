package com.project_spring.Admin.Service.User;

import com.project_spring.Admin.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao implements IUserDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean isExist(User user) {
        String query = "SELECT username, password FROM Account WHERE username = ? AND password = ?";
        int countRows = 0;
        return jdbcTemplate.query(query, new Object[]{user.getUserName(), user.getPassword()}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }).size() > 0;
    }

    @Override
    public void addUser(User user) {
        if(!isExist(user)) {
            String query = "INSERT INTO Account(username, password) VALUES(?, ?)";
            jdbcTemplate.update(query, user.getUserName(), user.getPassword());
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUser() {
        String query = "SELECT username, password FROM Account";
        return jdbcTemplate.query(query, new Object[]{}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
    }
}
