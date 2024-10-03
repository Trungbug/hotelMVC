package com.project_spring.Admin.DAO.Customer;

import com.project_spring.Admin.Model.Customer;
import com.project_spring.Admin.Model.RoomType;

import java.util.List;

public interface ICustomerDao {
    int getCurrentId();

    boolean addCustomer(Customer customer);

    boolean deleteCustomer(int customerId);

    boolean updateCustomer(Customer customer);

    boolean isExistCustomer(Customer customer);
    List<Customer> displayAllCustomer();
    Customer findCustomerById(int id);
}
