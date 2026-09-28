package com.example.elibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.elibrary.model.SchoolDistrict;
import com.example.elibrary.repository.SchoolDistrictRepository;

@Service
public class SchoolDistrictService {
    private final SchoolDistrictRepository schoolDistrictRepository;

    public SchoolDistrictService(SchoolDistrictRepository schoolDistrictRepository) {
        this.schoolDistrictRepository = schoolDistrictRepository;
    }

    public SchoolDistrict addSchoolDistrict(SchoolDistrict schoolDistrict) {
        return schoolDistrictRepository.save(schoolDistrict);
    }

    public List<SchoolDistrict> getAllSchoolDistricts() {
        return schoolDistrictRepository.findAll();
    }

    public SchoolDistrict getSchoolDistrictById(Long id) {
        return schoolDistrictRepository.findById(id).orElse(null);
    }

    public SchoolDistrict updateSchoolDistrict(Long id, SchoolDistrict updatedDistrict) {
        SchoolDistrict existing = schoolDistrictRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setName(updatedDistrict.getName());
        existing.setContactPerson(updatedDistrict.getContactPerson());
        existing.setEmail(updatedDistrict.getEmail());
        existing.setPhone(updatedDistrict.getPhone());
        existing.setAddress(updatedDistrict.getAddress());
        return schoolDistrictRepository.save(existing);
    }

    public void deleteSchoolDistrict(Long id) {
        schoolDistrictRepository.deleteById(id);
    }
}
