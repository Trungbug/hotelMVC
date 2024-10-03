package com.project_spring.Admin.DAO.Booking;

import com.project_spring.Admin.Model.Booking;
import com.project_spring.Admin.Model.Customer;
import com.project_spring.Admin.Model.Room;
import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Service.Customer.CustomerService;
import com.project_spring.Admin.Service.Room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookingDao implements IBookingDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RoomService roomService;

    @Autowired
    CustomerService customerService;

    @Override
    public boolean addBooking(Booking booking) {
        String query = "INSERT INTO BOOKING (CustomerId, RoomId, CheckInDate, CheckOutDate, NumberOfPeople, RequiredSpecial, TotalAmount) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, booking.getCustomer().getCustomerId(), booking.getRoom().getRoomId(), booking.getCheckIn(), booking.getCheckOut(), booking.getNumberOfPeople(), booking.getRequiredSpecial(), booking.getTotalAmount()) > 0;
    }

    @Override
    public boolean deleteBooking(int id) {
        String query = "DELETE FROM BOOKING WHERE BookingId = ?";
        return jdbcTemplate.update(query, id) > 0;
    }

    @Override
    public boolean updateBooking(Booking booking) {
        String query = "UPDATE BOOKING SET CustomerId = ?, RoomId = ?, CheckInDate = ?, CheckOutDate = ?, NumberOfPeople = ?, RequiredSpecial = ?, TotalAmount = ? WHERE BookingId = ?";
        return jdbcTemplate.update(query, booking.getCustomer().getCustomerId(), booking.getRoomId(), booking.getCheckIn(), booking.getCheckOut(), booking.getNumberOfPeople(), booking.getRequiredSpecial(), booking.getTotalAmount(), booking.getBookingId()) > 0;
    }

    @Override
    public List<Booking> displayAllBooking() {
        String query = "SELECT * FROM BOOKING";
        List<Booking> bookings = jdbcTemplate.query(query, new Object[]{}, new RowMapper<Booking>() {
            @Override
            public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
                Booking booking = new Booking();

                Room room = roomService.finhRoomById(rs.getInt("RoomId"));
                booking.setRoom(room);

                Customer customer = customerService.findCustomerById(rs.getInt("CustomerId"));
                booking.setCustomer(customer);

                booking.setBookingId(rs.getInt("BookingId"));
                booking.setCheckIn(rs.getDate("CheckInDate"));
                booking.setCheckOut(rs.getDate("CheckOutDate"));
                booking.setNumberOfPeople(rs.getInt("NumberOfPeople"));
                booking.setRequiredSpecial(rs.getString("RequiredSpecial"));
                booking.setTotalAmount(rs.getDouble("TotalAmount"));
                booking.setStatus(rs.getInt("status"));
                booking.setPaymentStatus(rs.getString("PaymentStatus"));
                return booking;
            }
        });
        return bookings;
    }

    @Override
    public Booking findBookingById(int id) {
        String query = "SELECT * FROM BOOKING WHERE BookingId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Booking>() {
            @Override
            public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
                Booking booking = new Booking();

                Room room = roomService.finhRoomById(rs.getInt("RoomId"));
                booking.setRoom(room);

                Customer customer = customerService.findCustomerById(rs.getInt("CustomerId"));
                booking.setCustomer(customer);

                booking.setBookingId(rs.getInt("BookingId"));
                booking.setCheckIn(rs.getDate("CheckInDate"));
                booking.setCheckOut(rs.getDate("CheckOutDate"));
                booking.setNumberOfPeople(rs.getInt("NumberOfPeople"));
                booking.setRequiredSpecial(rs.getString("RequiredSpecial"));
                booking.setTotalAmount(rs.getDouble("TotalAmount"));
                booking.setStatus(rs.getInt("status"));
                booking.setPaymentStatus(rs.getString("PaymentStatus"));
                return booking;
            }
        });
    }
}
