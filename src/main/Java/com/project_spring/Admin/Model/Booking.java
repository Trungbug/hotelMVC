package com.project_spring.Admin.Model;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class Booking {
    private int bookingId;
    private int customerId;
    private Customer customer;
    private int roomId;
    private Room room;
    private Date checkIn;
    private Date checkOut;
    private int numberOfPeople;
    private String requiredSpecial;
    private double totalAmount;
    private int status = 0;
    private String PaymentStatus;

    public Booking() {
    }

    public Booking(int bookingId, int customerId, Customer customer, int roomId, Room room, Date checkIn, Date checkOut, int numberOfPeople, String requiredSpecial, double totalAmount, int status, String paymentStatus) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.customer = customer;
        this.roomId = roomId;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfPeople = numberOfPeople;
        this.requiredSpecial = requiredSpecial;
        this.totalAmount = totalAmount;
        this.status = status;
        PaymentStatus = paymentStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getRequiredSpecial() {
        return requiredSpecial;
    }

    public void setRequiredSpecial(String requiredSpecial) {
        this.requiredSpecial = requiredSpecial;
    }

    public double getTotalAmount() {
        long diffInMillies = Math.abs(checkOut.getTime() - checkIn.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return ++diff * room.getPricePerNight();
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }
}