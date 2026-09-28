package com.example.elibrary.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String journalNumber;
    private String journalType;
    private LocalDate journalDate;
    private String description;

    @OneToMany(mappedBy = "journal")
    @JsonIgnore
    private List<JournalEntry> entries = new ArrayList<>();

    public Journal() {
    }

    public Journal(String journalNumber, String journalType, LocalDate journalDate, String description) {
        this.journalNumber = journalNumber;
        this.journalType = journalType;
        this.journalDate = journalDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getJournalNumber() {
        return journalNumber;
    }

    public void setJournalNumber(String journalNumber) {
        this.journalNumber = journalNumber;
    }

    public String getJournalType() {
        return journalType;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    public LocalDate getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(LocalDate journalDate) {
        this.journalDate = journalDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JournalEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<JournalEntry> entries) {
        this.entries = entries;
    }
}
