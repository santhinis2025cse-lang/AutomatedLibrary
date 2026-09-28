package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.model.VendorBill;

public interface VendorBillRepository extends JpaRepository<VendorBill, Long> {
}
