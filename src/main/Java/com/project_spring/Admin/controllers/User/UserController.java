package com.project_spring.Admin.controllers.User;

import com.project_spring.Admin.Model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/danh-sach-user", method = RequestMethod.GET)
    public String displayAllUser(HttpServletRequest httpServletRequest)  {
        List<User> users = userService.getAllUser();
        httpServletRequest.setAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String addUser(HttpServletRequest httpServletRequest) {
        User user = new User();
        httpServletRequest.setAttribute("user", user);
        return "form-user";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUser(HttpServletRequest httpServletRequest, @ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "form-user";
        }
        userService.addUser(user);
        List<User> users = userService.getAllUser();
        httpServletRequest.setAttribute("users", users);
        return "users";
    }
}
