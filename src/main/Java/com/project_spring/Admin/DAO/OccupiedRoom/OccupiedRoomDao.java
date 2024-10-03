package com.project_spring.Admin.DAO.OccupiedRoom;

import com.project_spring.Admin.DAO.Booking.BookingDao;
import com.project_spring.Admin.DAO.Customer.CustomerDao;
import com.project_spring.Admin.DAO.Room.RoomDao;
import com.project_spring.Admin.Model.Booking;
import com.project_spring.Admin.Model.Customer;
import com.project_spring.Admin.Model.OccupiedRoom;
import com.project_spring.Admin.Model.Room;
import com.project_spring.Admin.Service.Booking.BookingService;
import com.project_spring.Admin.Service.Customer.CustomerService;
import com.project_spring.Admin.Service.Room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OccupiedRoomDao implements IOccupiedRoomDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BookingService bookingService;

    @Autowired
    RoomService roomService;

    @Autowired
    CustomerService customerService;

    @Override
    public List<OccupiedRoom> displayInfor() {
        List<OccupiedRoom> occupiedRooms = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder("select * \n" +
                    "from booking\n" +
                    "inner join room\n" +
                    "on booking.roomid = room.roomid\n" +
                    "inner join customer\n" +
                    "on booking.customerid = customer.customerid\n" +
                    "where room.status != \"Trống\";");

            occupiedRooms = jdbcTemplate.query(query.toString(), new Object[]{}, new RowMapper<OccupiedRoom>() {
                @Override
                public OccupiedRoom mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OccupiedRoom occupiedRoom = new OccupiedRoom();
                    Booking booking = bookingService.findBookingById(rs.getInt("BookingId"));
                    occupiedRoom.setBooking(booking);
                    Room room = roomService.finhRoomById(rs.getInt("RoomId"));
                    occupiedRoom.setRoom(room);
                    Customer customer = customerService.findCustomerById(rs.getInt("CustomerId"));
                    occupiedRoom.setCustomer(customer);
                    return occupiedRoom;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return occupiedRooms;
    }

    @Override
    public boolean checkIn(Booking booking) {
        try {
            Room room = booking.getRoom();
            roomService.upadateStatus(room, "Đang sử dụng");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
