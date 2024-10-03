package com.project_spring.Admin.controllers.User;

import com.project_spring.Admin.Model.User;

import java.util.List;

public interface IUserService {
    boolean isExist(User user);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getAllUser();
}
