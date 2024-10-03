package com.project_spring.Admin.Service.User;

import com.project_spring.Admin.Model.User;

import java.util.List;

public interface IUserDao {
    boolean isExist(User user);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getAllUser();
}
