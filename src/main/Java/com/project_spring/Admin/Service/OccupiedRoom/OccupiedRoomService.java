package com.project_spring.Admin.Service.OccupiedRoom;

import com.project_spring.Admin.DAO.OccupiedRoom.OccupiedRoomDao;
import com.project_spring.Admin.Model.Booking;
import com.project_spring.Admin.Model.OccupiedRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupiedRoomService implements IOccupiedRoomService{
    @Autowired
    OccupiedRoomDao occupiedRoomDao;

    @Override
    public List<OccupiedRoom> displayInfor() {
        return occupiedRoomDao.displayInfor();
    }

    @Override
    public boolean checkIn(Booking booking) {
        return occupiedRoomDao.checkIn(booking);
    }
}
