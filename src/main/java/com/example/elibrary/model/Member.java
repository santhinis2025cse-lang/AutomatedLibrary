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
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private LocalDate membershipStart;
    private LocalDate membershipExpiry;
    private String status;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Checkout> checkouts = new ArrayList<>();

    public Member() {
    }

    public Member(String name, String email, String phone, LocalDate membershipStart, LocalDate membershipExpiry, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipStart = membershipStart;
        this.membershipExpiry = membershipExpiry;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getMembershipStart() {
        return membershipStart;
    }

    public void setMembershipStart(LocalDate membershipStart) {
        this.membershipStart = membershipStart;
    }

    public LocalDate getMembershipExpiry() {
        return membershipExpiry;
    }

    public void setMembershipExpiry(LocalDate membershipExpiry) {
        this.membershipExpiry = membershipExpiry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }
}
