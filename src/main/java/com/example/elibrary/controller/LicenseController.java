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

import com.example.elibrary.model.License;
import com.example.elibrary.service.LicenseService;

@RestController
@RequestMapping("/api/elibrary/licenses")
public class LicenseController {

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping
    public License addLicense(
            @RequestBody License license) {

        return licenseService.addLicense(license);
    }

    @GetMapping
    public List<License> getAllLicenses() {

        return licenseService.getAllLicenses();
    }

    @GetMapping("/{id}")
    public License getLicenseById(
            @PathVariable Long id) {

        return licenseService.getLicenseById(id);
    }

    @PutMapping("/{id}")
    public License updateLicense(
            @PathVariable Long id,
            @RequestBody License license) {

        return licenseService.updateLicense(id, license);
    }

    @DeleteMapping("/{id}")
    public void deleteLicense(
            @PathVariable Long id) {

        licenseService.deleteLicense(id);
    }
}