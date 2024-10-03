package com.project_spring.Admin.controllers.Facilities;

import com.project_spring.Admin.DAO.Facilities.FacilitiesDao;
import com.project_spring.Admin.Model.Facilities;
import com.project_spring.Admin.Service.Facilities.FacilitiesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class FacilitiesRestController {
    @Autowired
    FacilitiesService facilitiesService;
    @Autowired
    FacilitiesDao facilitiesDao;
    @GetMapping(value = "/allListFacilities")
    public @ResponseBody List<Facilities> listFacilities(){
        return facilitiesService.displayAllFacilities();
    }
    @GetMapping(value = "/cosovatchat/{id}")
    public Facilities getFacilityById(@PathVariable("id") int id) {
        Facilities facilities = facilitiesService.findFacilitiesById(id);
        return facilities;
    }
    @PostMapping(value = "/addcsvc")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> addcsvc(@RequestBody Facilities facilities){
        try{
            facilitiesService.addFacilities(facilities);
            return new ResponseEntity<>("Them thanh cong",HttpStatus.CREATED);
        }
        catch (DuplicateKeyException exception){
            return new ResponseEntity<>("Da ton tai",HttpStatus.CONFLICT);
        }
    }
    @PostMapping(value = "/themcsvc")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Facilities themcsvc(@RequestBody Facilities facilities){
        facilitiesService.addFacilities(facilities);
        return facilities;
    }
    @DeleteMapping(value= "/deletecsvc/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> deletecsvc(@PathVariable("id") int id){
        // Kiểm tra xem mục có tồn tại trong cơ sở dữ liệu không
        boolean exists = facilitiesDao.checkExists(id);
        if (!exists) {
            return new ResponseEntity<>("Không tồn tại trong cơ sở dữ liệu", HttpStatus.NOT_FOUND);
        }

        try {
            facilitiesService.deleteFacilities(id);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.CREATED);
        } catch(EmptyResultDataAccessException ex) {
            return new ResponseEntity<>("Không tồn tại trong cơ sở dữ liệu", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/update/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> updatecsvc(@PathVariable ("id") int id, @RequestBody Facilities facilities){
        boolean exists = facilitiesDao.checkExists(id);
        if (!exists) {
            return new ResponseEntity<>("Không tồn tại trong cơ sở dữ liệu", HttpStatus.NOT_FOUND);
        }
        try {
            facilities.setId(id);
            facilitiesService.updateFacilities(facilities);
            return new ResponseEntity<>("Sua thành công", HttpStatus.CREATED);
        } catch(EmptyResultDataAccessException ex) {
            return new ResponseEntity<>("Không tồn tại trong cơ sở dữ liệu", HttpStatus.NOT_FOUND);
        }

    }


}
