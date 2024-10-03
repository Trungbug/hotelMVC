package com.project_spring.Admin.controllers.FacilitiesType;

import com.project_spring.Admin.Model.FacilitiesType;
import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Service.FacilitiesType.FacilitiesTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FacilitiesTypeController {
    @Autowired
    FacilitiesTypeService facilitiesTypeService;
    @GetMapping(value = "/list-facilities-type")
    public @ResponseBody ResponseEntity<?> listAllFacilitiesTypeAPI() {
        try {
            List<FacilitiesType> facilitiesTypes = facilitiesTypeService.displayAllFacilitiesType();
            return ResponseEntity.ok(facilitiesTypes);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xay ra loi: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/xoa-loai-co-so-vat-chat/id={id}")
    public @ResponseBody ResponseEntity<?> deleteFacilitiesTypeAPI(@PathVariable("id") String id) {
        try {
            FacilitiesType facilitiesType = facilitiesTypeService.findFacilitiesTypeById(id);
            facilitiesTypeService.deleteFacilitiesType(id);
            facilitiesType.setId(id);
            return ResponseEntity.ok(facilitiesType);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xay ra loi: " + e.getMessage());
        }
    }

    @PostMapping(value = "/add-facilities-type")
    public @ResponseBody ResponseEntity<?> addFacilitiesTypeAPI(@RequestBody FacilitiesType facilitiesType) {
        try {
            facilitiesTypeService.addFacilitiesType(facilitiesType);
            return ResponseEntity.ok(facilitiesType);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xay ra loi: " + e.getMessage());
        }
    }

    @PutMapping(value = "/edit-facilities-type/id={id}")
    public @ResponseBody ResponseEntity<?> editFacilitiesTypeAPI(@PathVariable("id") String id, @RequestBody FacilitiesType facilitiesType) {
        try {
            facilitiesType.setId(id);
            facilitiesTypeService.updateFacilitiesType(facilitiesType);
            return ResponseEntity.ok(facilitiesType);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xay ra loi: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/danh-sach-loai-co-so-vat-chat", method = RequestMethod.GET)
    public String displayAllFacilitiesType(HttpServletRequest httpServletRequest) {

        return "Admin/FacilitiesType/list-facilities-type";
    }

    @GetMapping(value = "/find-facilities-type/id={id}")
    public @ResponseBody ResponseEntity<?> listAllFacilitiesTypeAPI(@PathVariable("id") String id) {
        try {
            FacilitiesType facilitiesType = facilitiesTypeService.findFacilitiesTypeById(id);
            return ResponseEntity.ok(facilitiesType);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xay ra loi: " + e.getMessage());
        }
    }
    @RequestMapping(value = "/them-loai-co-so-vat-chat", method = RequestMethod.GET)
    public String addFacilitiesType(HttpServletRequest httpServletRequest) {
        FacilitiesType facilitiesType = new FacilitiesType();
        httpServletRequest.setAttribute("facilitiesType", facilitiesType);
        return "Admin/FacilitiesType/add-facilities-type";
    }

    @RequestMapping(value = "/them-loai-co-so-vat-chat", method = RequestMethod.POST)
    public String addFacilitiesType(HttpServletRequest httpServletRequest, @ModelAttribute("facilitiesType") @Valid FacilitiesType facilitiesType,
                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "Admin/FacilitiesType/add-facilities-type";
        }
        boolean result = facilitiesTypeService.addFacilitiesType(facilitiesType);
        return result ? "redirect:/danh-sach-loai-co-so-vat-chat" : "Admin/FacilitiesType/add-facilities-type";
    }

    @RequestMapping(value = "/xoa-loai-co-so-vat-chat/id={id}", method = RequestMethod.GET)
    public String deleteFacilitiesType(HttpServletRequest httpServletRequest, @PathVariable(name="id") String id) {
        boolean result = facilitiesTypeService.deleteFacilitiesType(id);
        if(!result) {
            return "Admin/Error/message-error-delete";

        }
        return "redirect:/danh-sach-loai-co-so-vat-chat";
    }

    @RequestMapping(value = "/sua-loai-co-so-vat-chat/id={id}", method = RequestMethod.GET)
    public String editFacilitiesType(HttpServletRequest httpServletRequest, @PathVariable(name="id") String id) {
        FacilitiesType facilitiesType = facilitiesTypeService.findFacilitiesTypeById(id);
        httpServletRequest.setAttribute("facilitiesType", facilitiesType);
        return "Admin/FacilitiesType/edit-facilities-type";
    }


    @RequestMapping(value = "/sua-loai-co-so-vat-chat", method = RequestMethod.POST)
    public String editFacilitiesType(HttpServletRequest httpServletRequest, @ModelAttribute("facilitiesType") FacilitiesType facilitiesType,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "Admin/FacilitiesType/edit-facilities-type";
        }
        boolean result = facilitiesTypeService.updateFacilitiesType(facilitiesType);
        return result ? "redirect:/danh-sach-loai-co-so-vat-chat" : "Admin/FacilitiesType/edit-facilities-type";
    }
}