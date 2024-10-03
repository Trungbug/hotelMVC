package com.project_spring.Admin.DAO.RoomType;

import com.project_spring.Admin.Model.RoomType;

import java.util.List;

public interface IRoomTypeDao {
    boolean addRoomType(RoomType roomType) throws Exception;

    boolean deleteRoomType(int roomTypeId);

    boolean updateRoomType(RoomType roomType);

    boolean isExistRoomType(RoomType roomType);
    List<RoomType> displayAllRoomType();
    RoomType findRoomTypeById(int id);
}
