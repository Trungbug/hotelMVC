package com.project_spring.Admin.controllers.DashBoard;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashBoardController {
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashBoard(HttpServletRequest httpServletRequest) {
        return "Admin/DashBoard/DashBoard";
    }
}