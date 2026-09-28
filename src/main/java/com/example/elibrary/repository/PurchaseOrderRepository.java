package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
