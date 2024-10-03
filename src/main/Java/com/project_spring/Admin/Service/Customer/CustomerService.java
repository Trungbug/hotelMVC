package com.project_spring.Admin.Service.Customer;

import com.project_spring.Admin.DAO.Customer.CustomerDao;
import com.project_spring.Admin.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    CustomerDao customerDao;

    @Override
    public int getCurrentId() {
        return customerDao.getCurrentId();
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        return customerDao.deleteCustomer(customerId);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public boolean isExistCustomer(Customer customer) {
        return customerDao.isExistCustomer(customer);
    }

    @Override
    public List<Customer> displayAllCustomer() {
        return customerDao.displayAllCustomer();
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerDao.findCustomerById(id);
    }
}
