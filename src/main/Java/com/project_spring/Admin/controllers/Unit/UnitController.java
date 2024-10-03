package com.project_spring.Admin.controllers.Unit;

import com.project_spring.Admin.Model.Unit;
import com.project_spring.Admin.Service.Unit.UnitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UnitController {
    @Autowired
    UnitService unitService;

    @GetMapping(value = "/list-unit")
    public @ResponseBody List<Unit> listAllUnitAPI() {
        List<Unit> units = unitService.listAllUnit();
        return units;
    }

    @GetMapping(value = "/find-unit/unitId={unitId}")
    public @ResponseBody Unit findUnitAPI(@PathVariable("unitId") int unitId) {
        Unit unit = unitService.findUnitById(unitId);
        return unit;
    }

    @GetMapping(value = "/danh-sach-loai-don-vi")
    public String listAllUnit() {
        return "Admin/Unit/list-unit";
    }

    @DeleteMapping(value = "/xoa-don-vi/unitId={unitId}")
    public @ResponseBody Unit deleteUnitAPI(@PathVariable("unitId") int unitId) {
        Unit unit = unitService.findUnitById(unitId);
        unit.setUnitId(unitId);
        unitService.deleteUnit(unitId);
        return unit;
    }


    @GetMapping(value = "/them-don-vi")
    public String addUnit() {
        return "Admin/Unit/add-unit";
    }

    @PostMapping(value = "/add-unit")
    public @ResponseBody Unit addUnitAPI(@RequestBody Unit unit) {
        unitService.addUnit(unit);
        return unit;
    }

    @GetMapping(value = "/sua-don-vi/unitId={unitId}")
    public String editUnit(HttpServletRequest httpServletRequest, @PathVariable("unitId") int unitId) {
        Unit unit = unitService.findUnitById(unitId);
        httpServletRequest.setAttribute("unit", unit);
        return "Admin/Unit/edit-unit";
    }

    @PutMapping(value = "/edit-unit/unitId={unitId}")
    public @ResponseBody Unit editUnitAPI(@PathVariable("unitId") int unitId, @RequestBody Unit unit) {
        unit.setUnitId(unitId);
        unitService.updateUnit(unit);
        return unit;
    }

}