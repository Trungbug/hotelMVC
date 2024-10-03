package com.project_spring.Admin.controllers.Room;

import com.project_spring.Admin.Model.Facilities;
import com.project_spring.Admin.Model.FacilitiesType;
import com.project_spring.Admin.Model.Room;
import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Service.Room.RoomService;
import com.project_spring.Admin.Service.RoomType.RoomTypeService;
import com.project_spring.Admin.Validator.RoomValidator;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoomController {
    @Autowired
    RoomService roomService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    RoomValidator roomValidator;

    @RequestMapping(value = "/danh-sach-phong", method = RequestMethod.GET)
    public String displayAllRooms(HttpServletRequest httpServletRequest) {
        List<Room> rooms = roomService.displayAllRoom();
        Map<Integer, String> roomMap = new HashMap<>();
        for(Room room : rooms) {
            String base64RoomImage = Base64.getEncoder().encodeToString(room.getImage());
            int roomId = room.getRoomId();
            roomMap.put(roomId, base64RoomImage);
        }
        httpServletRequest.setAttribute("rooms", rooms);
//        httpServletRequest.setAttribute("base64_RoomImage", base64Image);
        httpServletRequest.setAttribute("roomMap", roomMap);
        return "Admin/Room/list-room";
    }

    @RequestMapping(value = "/them-phong", method = RequestMethod.GET)
    public String addRoom(HttpServletRequest httpServletRequest) {
        Room room = new Room();
        httpServletRequest.setAttribute("room", room);

        List<RoomType> roomTypes = roomTypeService.displayAllRoomType();
        httpServletRequest.setAttribute("roomTypes", roomTypes);

        return "Admin/Room/add-room";
    }

    @RequestMapping(value = "/them-phong", method = RequestMethod.POST)
    public String addRoom(HttpServletRequest httpServletRequest, @ModelAttribute("room") @Valid Room room,
                                BindingResult bindingResult) throws Exception {
        roomValidator.validate(room, bindingResult);
        if(bindingResult.hasErrors()) {
            return "Admin/Room/add-room";
        }

        RoomType roomType = roomTypeService.findRoomTypeById(room.getRoomTypeId());
        room.setRoomType(roomType);
        boolean result = roomService.addRoom(room);
        System.out.println(room.getImage());
        return result ? "redirect:/danh-sach-phong" : "Admin/Room/add-room";
    }

    @RequestMapping(value = "/xoa-phong/id={id}", method = RequestMethod.GET)
    public String deleteRoom(HttpServletRequest httpServletRequest, @PathVariable(name="id") int id) {
        boolean result = roomService.deleteRoom(id);
        return "redirect:/danh-sach-phong";
    }

    @RequestMapping(value = "/sua-phong/id={id}", method = RequestMethod.GET)
    public String editFacilities(HttpServletRequest httpServletRequest, @PathVariable(name="id") int id) {
        Room room = roomService.finhRoomById(id);
        RoomType roomType = roomTypeService.findRoomTypeById(room.getRoomTypeId());
        room.setRoomType(roomType);
        httpServletRequest.setAttribute("room", room);

        List<RoomType> roomTypes = roomTypeService.displayAllRoomType();
        httpServletRequest.setAttribute("roomTypes", roomTypes);

        return "Admin/Room/edit-room";
    }

    @RequestMapping(value = "/sua-phong", method = RequestMethod.POST)
    public String editRoom(HttpServletRequest httpServletRequest, @ModelAttribute("room") Room room,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "Admin/Room/edit-room";
        }
        boolean result = roomService.updateRoom(room);
        return "redirect:/danh-sach-phong";
    }


}