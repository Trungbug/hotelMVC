package com.project_spring.Admin.controllers.Facilities;

import com.project_spring.Admin.Model.Facilities;
import com.project_spring.Admin.Model.FacilitiesType;
import com.project_spring.Admin.Service.Facilities.FacilitiesService;
import com.project_spring.Admin.Service.FacilitiesType.FacilitiesTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FacilitiesController {
    @Autowired
    FacilitiesService facilitiesService;

    @Autowired
    FacilitiesTypeService facilitiesTypeService;

    @RequestMapping(value = "/danh-sach-co-so-vat-chat", method = RequestMethod.GET)
    public String displayAllFacilities(HttpServletRequest httpServletRequest) {
        List<Facilities> facilities = facilitiesService.displayAllFacilities();
        httpServletRequest.setAttribute("facilities", facilities);
        return "Admin/Facilities/list-facilities";
    }
    @RequestMapping(value = "/them-co-so-vat-chat", method = RequestMethod.GET)
    public String addFacilities(HttpServletRequest httpServletRequest) {
        Facilities facilities = new Facilities();
        httpServletRequest.setAttribute("facilities", facilities);

        List<FacilitiesType> facilitiesTypes = facilitiesTypeService.displayAllFacilitiesType();
        httpServletRequest.setAttribute("facilitiesTypes", facilitiesTypes);

        return "Admin/Facilities/add-facilities";
    }

    @RequestMapping(value = "/them-co-so-vat-chat", method = RequestMethod.POST)
    public String addFacilities(HttpServletRequest httpServletRequest,
                                @ModelAttribute("facilities") @Valid Facilities facilities,
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "Admin/Facilities/add-facilities";
        }

        FacilitiesType facilitiesType = facilitiesTypeService.findFacilitiesTypeById(facilities.getFacilitiesTypeId());
        facilities.setFacilitiesType(facilitiesType);
        boolean result = facilitiesService.addFacilities(facilities);
        return result ? "redirect:/danh-sach-co-so-vat-chat" : "Admin/Facilities/add-facilities";
    }



    @RequestMapping(value = "/xoa-co-so-vat-chat/id={id}", method = RequestMethod.GET)
    public String deleteFacilities(HttpServletRequest httpServletRequest, @PathVariable(name="id") int id) {
        boolean result = facilitiesService.deleteFacilities(id);
        return "redirect:/danh-sach-co-so-vat-chat";
    }

    @RequestMapping(value = "/sua-co-so-vat-chat/id={id}", method = RequestMethod.GET)
    public String editFacilities(HttpServletRequest httpServletRequest, @PathVariable(name="id") int id) {
//        Facilities facilities = facilitiesService.findFacilitiesById(id);
//        FacilitiesType facilitiesType = facilitiesTypeService.findFacilitiesTypeById(facilities.getFacilitiesTypeId());
//        facilities.setFacilitiesType(facilitiesType);
//        httpServletRequest.setAttribute("facilities", facilities);
//
//        List<FacilitiesType> facilitiesTypes = facilitiesTypeService.displayAllFacilitiesType();
//        httpServletRequest.setAttribute("facilitiesTypes", facilitiesTypes);

        return "Admin/Facilities/edit-facilities";
    }

    @RequestMapping(value = "/sua-co-so-vat-chat", method = RequestMethod.POST)
    public String editFacilities(HttpServletRequest httpServletRequest, @ModelAttribute("facilities") Facilities facilities,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "Admin/Facilities/edit-facilities";
        }
        boolean result = facilitiesService.updateFacilities(facilities);
        return "redirect:/danh-sach-co-so-vat-chat";
    }
}