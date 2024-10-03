package com.project_spring.Admin.Service.Room;

import com.project_spring.Admin.DAO.Room.RoomDao;
import com.project_spring.Admin.Model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;

@Service
public class RoomService implements IRoomService{
    @Autowired
    RoomDao roomDao;

    @Override
    public boolean addRoom(Room room) throws Exception {
        return roomDao.addRoom(room);
    }

    @Override
    public boolean deleteRoom(int roomId) {
        return roomDao.deleteRoom(roomId);
    }

    @Override
    public boolean updateRoom(Room room) {
       return roomDao.updateRoom(room);
    }

    @Override
    public boolean findRoomByRoomType(int id) {
        return roomDao.findRoomByRoomType(id);
    }

    @Override
    public boolean upadateStatus(Room room, String status) {
        return roomDao.upadateStatus(room, status);
    }

    @Override
    public Room finhRoomById(int id) {
        return roomDao.finhRoomById(id);
    }

    @Override
    public boolean isExistRoom(Room room) {
        return roomDao.isExistRoom(room);
    }

    @Override
    public List<Room> displayAllRoom() {
        return roomDao.displayAllRoom();
    }

    @Override
    public Blob getImageByRoomId(int roomId) {
        return roomDao.getImageByRoomId(roomId);
    }
}
