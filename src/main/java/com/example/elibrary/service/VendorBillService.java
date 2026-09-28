package com.example.elibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.elibrary.model.VendorBill;
import com.example.elibrary.repository.VendorBillRepository;

@Service
public class VendorBillService {
    private final VendorBillRepository vendorBillRepository;

    public VendorBillService(VendorBillRepository vendorBillRepository) {
        this.vendorBillRepository = vendorBillRepository;
    }

    public VendorBill addVendorBill(VendorBill vendorBill) {
        return vendorBillRepository.save(vendorBill);
    }

    public List<VendorBill> getAllVendorBills() {
        return vendorBillRepository.findAll();
    }

    public VendorBill getVendorBillById(Long id) {
        return vendorBillRepository.findById(id).orElse(null);
    }

    public VendorBill updateVendorBill(Long id, VendorBill updatedBill) {
        VendorBill existing = vendorBillRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setBillNumber(updatedBill.getBillNumber());
        existing.setAmount(updatedBill.getAmount());
        existing.setBillDate(updatedBill.getBillDate());
        existing.setStatus(updatedBill.getStatus());
        existing.setPaymentStatus(updatedBill.getPaymentStatus());
        existing.setPublisher(updatedBill.getPublisher());
        existing.setPurchaseOrder(updatedBill.getPurchaseOrder());
        return vendorBillRepository.save(existing);
    }

    public void deleteVendorBill(Long id) {
        vendorBillRepository.deleteById(id);
    }
}
