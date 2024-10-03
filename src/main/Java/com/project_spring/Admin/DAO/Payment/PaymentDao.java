package com.project_spring.Admin.DAO.Payment;

import com.project_spring.Admin.Model.*;
import com.project_spring.Admin.Service.Booking.BookingService;
import com.project_spring.Admin.Service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentDao implements IPaymentDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BookingService bookingService;

    @Autowired
    CustomerService customerService;

    @Override
    public List<Payment> listAll() {
        StringBuilder query = new StringBuilder("select * from paymenttransaction\n" +
                "inner join booking\n" +
                "on paymenttransaction.bookingid = booking.bookingid\n" +
                "inner join customer\n" +
                "on booking.customerid = customer.customerid;\n");
        List<Payment> payments = new ArrayList<>();
        try {
            payments = jdbcTemplate.query(query.toString(), new Object[]{}, new RowMapper<Payment>() {
                @Override
                public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Payment payment = new Payment();
                    payment.setPaymentId(rs.getInt("TransactionId"));
                    Customer customer = customerService.findCustomerById(rs.getInt("CustomerId"));
                    Booking booking = bookingService.findBookingById(rs.getInt("BookingId"));
                    booking.setCustomer(customer);
                    payment.setBooking(booking);
                    payment.setTransactionAmount(rs.getDouble("TransactionAmount"));
                    payment.setRefund(rs.getDouble("Refun"));
                    payment.setTransactionDate(rs.getDate("TransactionDate"));
                    return payment;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return payments;
    }

    @Override
    public boolean insert(Payment payment) {
        StringBuilder query = new StringBuilder("Insert into paymenttransaction(BookingId, TransactionAmount, Refun, TransactionDate) values(?, ?, ?, ?)");
        try {
            jdbcTemplate.update(query.toString(), payment.getBooking().getBookingId(), payment.getTransactionAmount(), payment.getRefund(), payment.getTransactionDate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Payment payment) {
        StringBuilder query = new StringBuilder("UPDATE paymenttransaction(BookingId, TransactionAmount, Refun, TransactionDate) values(?, ?, ?, ?)");
        try {
            jdbcTemplate.update(query.toString(), payment.getBooking().getBookingId(), payment.getTransactionAmount(), payment.getRefund(), payment.getTransactionDate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int paymentId) {
        StringBuilder query = new StringBuilder("DELETE FROM paymenttransaction WHERE TransactionId = ?");
        try {
            jdbcTemplate.update(query.toString(), paymentId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Payment findByPaymenId(int paymentId) {
        StringBuilder query = new StringBuilder("select * from paymenttransaction\n" +
                "inner join booking\n" +
                "on paymenttransaction.bookingid = booking.bookingid\n" +
                "inner join customer\n" +
                "on booking.customerid = customer.customerid\n" +
                "where TransactionId = ?");
        return jdbcTemplate.queryForObject(query.toString(), new Object[] {paymentId}, new RowMapper<Payment>() {
            @Override
            public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("TransactionId"));
                Customer customer = customerService.findCustomerById(rs.getInt("CustomerId"));
                Booking booking = bookingService.findBookingById(rs.getInt("BookingId"));
                booking.setCustomer(customer);
                payment.setBooking(booking);
                payment.setTransactionAmount(rs.getDouble("TransactionAmount"));
                payment.setRefund(rs.getDouble("Refun"));
                payment.setTransactionDate(rs.getDate("TransactionDate"));
                return payment;
            }
        });
    }
}
