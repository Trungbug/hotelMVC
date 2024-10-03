package com.project_spring.Admin.Model;

public class OccupiedRoom {
    private Booking booking;
    private Room room;
    private Customer customer;

    public OccupiedRoom() {
    }

    public OccupiedRoom(Booking booking, Room room, Customer customer) {
        this.booking = booking;
        this.room = room;
        this.customer = customer;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
