package com.example.elibrary.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poNumber;
    private String status;
    private int quantity;
    private double amount;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToOne(mappedBy = "purchaseOrder")
    @JsonIgnore
    private VendorBill vendorBill;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String poNumber, String status, int quantity, double amount, LocalDate orderDate, Publisher publisher) {
        this.poNumber = poNumber;
        this.status = status;
        this.quantity = quantity;
        this.amount = amount;
        this.orderDate = orderDate;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public VendorBill getVendorBill() {
        return vendorBill;
    }

    public void setVendorBill(VendorBill vendorBill) {
        this.vendorBill = vendorBill;
    }
}
