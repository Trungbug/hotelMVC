package com.project_spring.Admin.Service.RoomType;

import com.project_spring.Admin.DAO.RoomType.RoomTypeDao;
import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomTypeService implements IRoomTypeService{
    @Autowired
    RoomTypeDao roomTypeDao;

    @Override
    public boolean addRoomType(RoomType roomType) {
        return roomTypeDao.addRoomType(roomType);
    }

    @Override
    public boolean deleteRoomType(int roomTypeId) {
        return roomTypeDao.deleteRoomType(roomTypeId);
    }

    @Override
    public boolean updateRoomType(RoomType roomType) {
        return roomTypeDao.updateRoomType(roomType);
    }

    @Override
    public boolean isExistRoomType(RoomType roomType) {
        return roomTypeDao.isExistRoomType(roomType);
    }

    @Override
    public List<RoomType> displayAllRoomType() {
        return roomTypeDao.displayAllRoomType();
    }

    @Override
    public RoomType findRoomTypeById(int id) {
        return roomTypeDao.findRoomTypeById(id);
    }
}
