package com.project_spring.Admin.DAO.Booking;

import com.project_spring.Admin.Model.Booking;
import com.project_spring.Admin.Model.Facilities;

import java.util.List;

public interface IBookingDao {
    boolean addBooking(Booking booking);

    boolean deleteBooking(int id);

    boolean updateBooking(Booking booking);

    List<Booking> displayAllBooking();

    Booking findBookingById(int id);
}