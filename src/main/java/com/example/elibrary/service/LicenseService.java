package com.example.elibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.elibrary.model.License;
import com.example.elibrary.repository.LicenseRepository;

@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public License addLicense(License license) {
        return licenseRepository.save(license);
    }

    public List<License> getAllLicenses() {
        return licenseRepository.findAll();
    }

    public License getLicenseById(Long id) {
        return licenseRepository.findById(id).orElse(null);
    }

    public License updateLicense(Long id, License license) {

        License existingLicense =
                licenseRepository.findById(id).orElse(null);

        if (existingLicense == null) {
            return null;
        }

        existingLicense.setTotalLicenses(license.getTotalLicenses());
        existingLicense.setStartDate(license.getStartDate());
        existingLicense.setEndDate(license.getEndDate());
        existingLicense.setEbook(license.getEbook());

        return licenseRepository.save(existingLicense);
    }

    public void deleteLicense(Long id) {
        licenseRepository.deleteById(id);
    }
}