package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
