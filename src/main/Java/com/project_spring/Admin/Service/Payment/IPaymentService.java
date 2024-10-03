package com.project_spring.Admin.Service.Payment;

import com.project_spring.Admin.Model.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> listAll();
    boolean insert(Payment payment);
    boolean update(Payment payment);
    boolean delete(int paymentId);
    Payment findByPaymenId(int paymentId);
}
