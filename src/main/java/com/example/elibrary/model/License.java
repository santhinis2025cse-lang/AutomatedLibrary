package com.example.elibrary.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "license")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalLicenses;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "ebook_id")
    private Ebook ebook;

    public License() {
    }

    public License(int totalLicenses, LocalDate startDate,
                   LocalDate endDate, Ebook ebook) {

        this.totalLicenses = totalLicenses;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ebook = ebook;
    }

    public Long getId() {
        return id;
    }

    public int getTotalLicenses() {
        return totalLicenses;
    }

    public void setTotalLicenses(int totalLicenses) {
        this.totalLicenses = totalLicenses;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Ebook getEbook() {
        return ebook;
    }

    public void setEbook(Ebook ebook) {
        this.ebook = ebook;
    }
}