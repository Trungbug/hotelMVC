package com.project_spring.Admin.Model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Payment implements Serializable {
    private int paymentId;
    private Booking booking;
    private double transactionAmount;
    private double refund;
    private Date transactionDate;

    public Payment() {
        transactionDate = Date.valueOf(LocalDate.now());
    }

    public Payment(int paymentId, Booking booking, double transactionAmount, double refund, Date transactionDate) {
        this.paymentId = paymentId;
        this.booking = booking;
        this.transactionAmount = transactionAmount;
        this.refund = refund;
        this.transactionDate = transactionDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getRefund() {
        return transactionAmount - booking.getTotalAmount();
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}