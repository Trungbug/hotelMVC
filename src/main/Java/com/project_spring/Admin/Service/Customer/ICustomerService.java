package com.project_spring.Admin.Service.Customer;

import com.project_spring.Admin.Model.Customer;

import java.util.List;

public interface ICustomerService {
    int getCurrentId();

    boolean addCustomer(Customer customer);

    boolean deleteCustomer(int customerId);

    boolean updateCustomer(Customer customer);

    boolean isExistCustomer(Customer customer);
    List<Customer> displayAllCustomer();
    Customer findCustomerById(int id);
}
