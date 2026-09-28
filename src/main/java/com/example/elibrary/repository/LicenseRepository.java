package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.model.License;

public interface LicenseRepository extends JpaRepository<License, Long> {
}
