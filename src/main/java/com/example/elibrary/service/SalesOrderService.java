package com.example.elibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.elibrary.model.SalesOrder;
import com.example.elibrary.repository.SalesOrderRepository;

@Service
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;

    public SalesOrderService(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    public SalesOrder addSalesOrder(SalesOrder salesOrder) {
        return salesOrderRepository.save(salesOrder);
    }

    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderRepository.findAll();
    }

    public SalesOrder getSalesOrderById(Long id) {
        return salesOrderRepository.findById(id).orElse(null);
    }

    public SalesOrder updateSalesOrder(Long id, SalesOrder updatedOrder) {
        SalesOrder existing = salesOrderRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setOrderNumber(updatedOrder.getOrderNumber());
        existing.setQuantity(updatedOrder.getQuantity());
        existing.setAmount(updatedOrder.getAmount());
        existing.setOrderDate(updatedOrder.getOrderDate());
        existing.setStatus(updatedOrder.getStatus());
        existing.setSchoolDistrict(updatedOrder.getSchoolDistrict());
        existing.setProduct(updatedOrder.getProduct());
        return salesOrderRepository.save(existing);
    }

    public void deleteSalesOrder(Long id) {
        salesOrderRepository.deleteById(id);
    }
}
