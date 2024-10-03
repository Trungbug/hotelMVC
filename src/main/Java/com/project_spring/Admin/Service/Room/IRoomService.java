package com.project_spring.Admin.Service.Room;

import com.project_spring.Admin.Model.Room;

import java.sql.Blob;
import java.util.List;

public interface IRoomService {
    boolean addRoom(Room room) throws Exception;

    boolean deleteRoom(int roomId);

    boolean updateRoom(Room room);

    boolean findRoomByRoomType(int id);

    boolean upadateStatus(Room room, String status);

    Room finhRoomById(int id);

    boolean isExistRoom(Room room);
    List<Room> displayAllRoom();
    Blob getImageByRoomId(int roomId);
}
