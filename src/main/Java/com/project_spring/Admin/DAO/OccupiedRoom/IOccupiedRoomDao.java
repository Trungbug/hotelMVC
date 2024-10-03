package com.project_spring.Admin.DAO.OccupiedRoom;

import com.project_spring.Admin.Model.Booking;
import com.project_spring.Admin.Model.OccupiedRoom;

import java.util.List;

public interface IOccupiedRoomDao {
    List<OccupiedRoom> displayInfor();
    boolean checkIn(Booking booking);
}
