package com.project_spring.Admin.controllers.User;

import com.project_spring.Admin.Model.User;
import com.project_spring.Admin.Service.User.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserDao userDao;

    @Override
    public boolean isExist(User user) {
        return userDao.isExist(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
