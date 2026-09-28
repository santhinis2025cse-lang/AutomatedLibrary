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

import com.example.elibrary.model.VendorBill;
import com.example.elibrary.service.VendorBillService;

@RestController
@RequestMapping("/api/elibrary")
public class VendorBillController {
    private final VendorBillService vendorBillService;

    public VendorBillController(VendorBillService vendorBillService) {
        this.vendorBillService = vendorBillService;
    }

    @GetMapping("/vendor-bills")
    public List<VendorBill> getAllVendorBills() {
        return vendorBillService.getAllVendorBills();
    }

    @GetMapping("/vendor-bills/{id}")
    public VendorBill getVendorBillById(@PathVariable Long id) {
        return vendorBillService.getVendorBillById(id);
    }

    @PostMapping("/vendor-bills")
    public VendorBill addVendorBill(@RequestBody VendorBill vendorBill) {
        return vendorBillService.addVendorBill(vendorBill);
    }

    @PutMapping("/vendor-bills/{id}")
    public VendorBill updateVendorBill(@PathVariable Long id, @RequestBody VendorBill vendorBill) {
        return vendorBillService.updateVendorBill(id, vendorBill);
    }

    @DeleteMapping("/vendor-bills/{id}")
    public void deleteVendorBill(@PathVariable Long id) {
        vendorBillService.deleteVendorBill(id);
    }
}
