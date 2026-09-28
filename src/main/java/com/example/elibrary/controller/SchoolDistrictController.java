package com.example.elibrary.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elibrary.model.SchoolDistrict;
import com.example.elibrary.service.SchoolDistrictService;

@RestController
@RequestMapping("/api/elibrary")
public class SchoolDistrictController {
    private final SchoolDistrictService schoolDistrictService;

    public SchoolDistrictController(SchoolDistrictService schoolDistrictService) {
        this.schoolDistrictService = schoolDistrictService;
    }

    @GetMapping("/school-districts")
    public List<SchoolDistrict> getAllSchoolDistricts() {
        return schoolDistrictService.getAllSchoolDistricts();
    }

    @GetMapping("/school-districts/{id}")
    public SchoolDistrict getSchoolDistrictById(@PathVariable Long id) {
        return schoolDistrictService.getSchoolDistrictById(id);
    }

    @PostMapping("/school-districts")
    public SchoolDistrict addSchoolDistrict(@RequestBody SchoolDistrict schoolDistrict) {
        return schoolDistrictService.addSchoolDistrict(schoolDistrict);
    }

    @PutMapping("/school-districts/{id}")
    public SchoolDistrict updateSchoolDistrict(@PathVariable Long id, @RequestBody SchoolDistrict schoolDistrict) {
        return schoolDistrictService.updateSchoolDistrict(id, schoolDistrict);
    }

    @DeleteMapping("/school-districts/{id}")
    public void deleteSchoolDistrict(@PathVariable Long id) {
        schoolDistrictService.deleteSchoolDistrict(id);
    }
}
