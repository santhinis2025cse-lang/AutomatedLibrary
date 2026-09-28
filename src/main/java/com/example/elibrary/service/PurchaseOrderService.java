package com.example.elibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.elibrary.model.PurchaseOrder;
import com.example.elibrary.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder updatedOrder) {
        PurchaseOrder existing = purchaseOrderRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setPoNumber(updatedOrder.getPoNumber());
        existing.setStatus(updatedOrder.getStatus());
        existing.setQuantity(updatedOrder.getQuantity());
        existing.setAmount(updatedOrder.getAmount());
        existing.setOrderDate(updatedOrder.getOrderDate());
        existing.setPublisher(updatedOrder.getPublisher());
        return purchaseOrderRepository.save(existing);
    }

    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
