package com.project_spring.Admin.Service.Payment;

import com.project_spring.Admin.DAO.Payment.PaymentDao;
import com.project_spring.Admin.Model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    PaymentDao paymentDao;

    @Override
    public List<Payment> listAll() {
        return paymentDao.listAll();
    }

    @Override
    public boolean insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public boolean update(Payment payment) {
        return paymentDao.update(payment);
    }

    @Override
    public boolean delete(int paymentId) {
        return paymentDao.delete(paymentId);
    }

    @Override
    public Payment findByPaymenId(int paymentId) {
        return paymentDao.findByPaymenId(paymentId);
    }
}
