package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.model.SchoolDistrict;

public interface SchoolDistrictRepository extends JpaRepository<SchoolDistrict, Long> {
}
