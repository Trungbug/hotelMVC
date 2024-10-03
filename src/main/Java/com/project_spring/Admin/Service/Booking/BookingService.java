package com.project_spring.Admin.Service.Booking;

import com.project_spring.Admin.DAO.Booking.BookingDao;
import com.project_spring.Admin.Model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService{
    @Autowired
    BookingDao bookingDao;

    @Override
    public boolean addBooking(Booking booking) {
        return bookingDao.addBooking(booking);
    }

    @Override
    public boolean deleteBooking(int id) {
        return bookingDao.deleteBooking(id);
    }

    @Override
    public boolean updateBooking(Booking booking) {
        return bookingDao.updateBooking(booking);
    }

    @Override
    public List<Booking> displayAllBooking() {
        return bookingDao.displayAllBooking();
    }

    @Override
    public Booking findBookingById(int id) {
        return bookingDao.findBookingById(id);
    }
}