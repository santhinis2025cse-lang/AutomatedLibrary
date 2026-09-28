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
@Table(name = "sales_order")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private int quantity;
    private double amount;
    private LocalDate orderDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "school_district_id")
    private SchoolDistrict schoolDistrict;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public SalesOrder() {
    }

    public SalesOrder(String orderNumber, int quantity, double amount, LocalDate orderDate, String status,
            SchoolDistrict schoolDistrict, Product product) {
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.amount = amount;
        this.orderDate = orderDate;
        this.status = status;
        this.schoolDistrict = schoolDistrict;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SchoolDistrict getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(SchoolDistrict schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
