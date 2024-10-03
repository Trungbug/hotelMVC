package com.project_spring.Admin.API;

import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Service.RoomType.RoomTypeService;
import com.project_spring.Admin.Utils.HttpUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = {"/api"})
public class NewApi extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    RoomTypeService roomTypeService;

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        RoomType roomType = HttpUtil.of(req.getReader()).toModel(RoomType.class);
//        if(roomTypeService.addRoomType(roomType)) {
//            objectMapper.writeValue(resp.getOutputStream(), "DA THEM THANH CONG");
//            System.out.println(roomType.getRoomTypeId() + "-" + roomType.getTypeName() + "-" + roomType.getDescription());
//        }
//        else {
//            System.out.println("Them khong thanh cong");
//        }
//    }
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        RoomType roomType = HttpUtil.of(req.getReader()).toModel(RoomType.class);
        if(roomTypeService.addRoomType(roomType)) {
            objectMapper.writeValue(resp.getOutputStream(), "DA THEM THANH CONG");
            System.out.println(roomType.getRoomTypeId() + "-" + roomType.getTypeName() + "-" + roomType.getDescription());
        }
        else {
            System.out.println("Them khong thanh cong");
        }
    }
}
