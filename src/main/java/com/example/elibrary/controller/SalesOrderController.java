package com.example.elibrary.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elibrary.model.SalesOrder;
import com.example.elibrary.service.SalesOrderService;

@RestController
@RequestMapping("/api/elibrary")
public class SalesOrderController {
    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @GetMapping("/sales-orders")
    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderService.getAllSalesOrders();
    }

    @GetMapping("/sales-orders/{id}")
    public SalesOrder getSalesOrderById(@PathVariable Long id) {
        return salesOrderService.getSalesOrderById(id);
    }

    @PostMapping("/sales-orders")
    public SalesOrder addSalesOrder(@RequestBody SalesOrder salesOrder) {
        return salesOrderService.addSalesOrder(salesOrder);
    }

    @PutMapping("/sales-orders/{id}")
    public SalesOrder updateSalesOrder(@PathVariable Long id, @RequestBody SalesOrder salesOrder) {
        return salesOrderService.updateSalesOrder(id, salesOrder);
    }

    @DeleteMapping("/sales-orders/{id}")
    public void deleteSalesOrder(@PathVariable Long id) {
        salesOrderService.deleteSalesOrder(id);
    }
}
