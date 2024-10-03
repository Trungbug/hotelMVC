package com.project_spring.Admin.controllers.OccupiedRoom;

import com.project_spring.Admin.Model.OccupiedRoom;
import com.project_spring.Admin.Service.OccupiedRoom.OccupiedRoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class OccupiedRoomController {
    @Autowired
    OccupiedRoomService occupiedRoomService;

    @RequestMapping(value = "/danh-sach-phong-co-nguoi-o", method = RequestMethod.GET)
    public String listInhabitedRoom(HttpServletRequest httpServletRequest) {
        List<OccupiedRoom> occupiedRooms = occupiedRoomService.displayInfor();
        httpServletRequest.setAttribute("occupiedRooms", occupiedRooms);
        return "Admin/Room/inhabited-room";
    }
}
