package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.model.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
}
