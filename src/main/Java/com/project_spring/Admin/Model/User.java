package com.project_spring.Admin.Model;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

public class User {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
