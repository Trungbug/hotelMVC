package com.project_spring.Admin.Service.OccupiedRoom;

import com.project_spring.Admin.Model.Booking;
import com.project_spring.Admin.Model.OccupiedRoom;

import java.util.List;

public interface IOccupiedRoomService {
    List<OccupiedRoom> displayInfor();
    boolean checkIn(Booking booking);
}
