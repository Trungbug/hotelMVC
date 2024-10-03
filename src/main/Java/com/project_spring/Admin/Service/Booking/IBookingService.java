package com.project_spring.Admin.Service.Booking;

import com.project_spring.Admin.Model.Booking;

import java.util.List;

public interface IBookingService {
    boolean addBooking(Booking booking);

    boolean deleteBooking(int id);

    boolean updateBooking(Booking booking);

    List<Booking> displayAllBooking();

    Booking findBookingById(int id);
}
