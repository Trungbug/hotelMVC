package com.project_spring.Admin.Service.RoomType;

import com.project_spring.Admin.Model.RoomType;

import java.util.List;

public interface IRoomTypeService {
    boolean addRoomType(RoomType roomType);

    boolean deleteRoomType(int roomTypeId);

    boolean updateRoomType(RoomType roomType);

    boolean isExistRoomType(RoomType roomType);
    List<RoomType> displayAllRoomType();
    RoomType findRoomTypeById(int id);
}
