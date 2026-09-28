package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}