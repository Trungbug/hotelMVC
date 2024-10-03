package com.project_spring.Admin.controllers.Booking;

import com.project_spring.Admin.Model.*;
import com.project_spring.Admin.Service.Booking.BookingService;
import com.project_spring.Admin.Service.Customer.CustomerService;
import com.project_spring.Admin.Service.OccupiedRoom.OccupiedRoomService;
import com.project_spring.Admin.Service.Room.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RoomService roomService;

    @Autowired
    OccupiedRoomService occupiedRoomService;

    @RequestMapping(value = "/danh-sach-dat-phong", method = RequestMethod.GET)
    public String displayAllBooking(HttpServletRequest httpServletRequest) {
        List<Booking> bookings = bookingService.displayAllBooking();
        httpServletRequest.setAttribute("bookings", bookings);
        return "Admin/Booking/list-booking";
    }

    @RequestMapping(value = "/dat-phong", method = RequestMethod.GET)
    public String addBooking(HttpServletRequest httpServletRequest) {
        Booking booking = new Booking();
        httpServletRequest.setAttribute("booking", booking);

        Customer customer = new Customer();
        httpServletRequest.setAttribute("customer", customer);

        List<Room> rooms = roomService.displayAllRoom();
        httpServletRequest.setAttribute("rooms", rooms);

        return "Admin/Booking/booking";
    }

    @RequestMapping(value = "/dat-phong", method = RequestMethod.POST)
    public String addBooking(HttpServletRequest httpServletRequest,
                             @ModelAttribute("booking") @Valid Booking booking,
                             @ModelAttribute("customer") @Valid Customer customer,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "Admin/Booking/booking";
        }
        Room room = roomService.finhRoomById(booking.getRoomId());
        booking.setRoom(room);

        customerService.addCustomer(customer);
        customer.setCustomerId(customerService.getCurrentId());
        booking.setCustomer(customer);

        boolean result = bookingService.addBooking(booking);
        return result ? "redirect:/danh-sach-dat-phong" : "Admin/Booking/booking";
    }

    @RequestMapping(value = "/xoa-dat-phong/id={id}", method = RequestMethod.GET)
    public String deleteBooking(HttpServletRequest httpServletRequest, @PathVariable(name="id") int id) {
        boolean result = bookingService.deleteBooking(id);
        if(!result) {
            return "Admin/Error/message-error-delete";
        }
        return "redirect:/danh-sach-dat-phong";
    }

    @RequestMapping(value = "/sua-dat-phong/id={id}", method = RequestMethod.GET)
    public String editBooking(HttpServletRequest httpServletRequest, @PathVariable(name="id") int id) {
        Booking booking = bookingService.findBookingById(id);
        Customer customer = customerService.findCustomerById(booking.getCustomer().getCustomerId());
        httpServletRequest.setAttribute("booking", booking);
        httpServletRequest.setAttribute("customer", customer);
        List<Room> rooms = roomService.displayAllRoom();
        httpServletRequest.setAttribute("rooms", rooms);
        return "Admin/Booking/edit-booking";
    }

    @RequestMapping(value = "/sua-dat-phong", method = RequestMethod.POST)
    public String editBooking(HttpServletRequest httpServletRequest,
                              @ModelAttribute("booking") Booking booking,
                              @ModelAttribute("customer") Customer customer,
                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "Admin/Booking/edit-booking";
        }
        Room room = roomService.finhRoomById(booking.getRoomId());
        booking.setRoom(room);
        customerService.updateCustomer(customer);
        booking.setCustomer(customer);
        boolean result = bookingService.updateBooking(booking);
        return result ? "redirect:/danh-sach-dat-phong" :"Admin/Booking/edit-booking";
    }

    @RequestMapping(value = "/nhan-phong-da-dat/id={id}", method = RequestMethod.GET)
    public String checkIn(HttpServletRequest httpServletRequest, @PathVariable(name="id") int id) {
        Booking booking = bookingService.findBookingById(id);
        boolean result = occupiedRoomService.checkIn(booking);
        return result ? "Admin/Booking/occupied-room" : "redirect:/danh-sach-dat-phong";
    }
}