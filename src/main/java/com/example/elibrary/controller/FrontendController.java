package com.example.elibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.elibrary.model.*;
import com.example.elibrary.service.*;

@Controller
public class FrontendController {

    private final MemberService memberService;
    private final ProductService productService;
    private final PublisherService publisherService;
    private final PurchaseOrderService purchaseOrderService;
    private final SalesOrderService salesOrderService;
    private final SchoolDistrictService schoolDistrictService;
    private final VendorBillService vendorBillService;
    private final EbookService ebookService;
    private final LicenseService licenseService;

    public FrontendController(
            MemberService memberService,
            ProductService productService,
            PublisherService publisherService,
            PurchaseOrderService purchaseOrderService,
            SalesOrderService salesOrderService,
            SchoolDistrictService schoolDistrictService,
            VendorBillService vendorBillService,
            EbookService ebookService,
            LicenseService licenseService) {
        this.memberService = memberService;
        this.productService = productService;
        this.publisherService = publisherService;
        this.purchaseOrderService = purchaseOrderService;
        this.salesOrderService = salesOrderService;
        this.schoolDistrictService = schoolDistrictService;
        this.vendorBillService = vendorBillService;
        this.ebookService = ebookService;
        this.licenseService = licenseService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ebookCount", ebookService.getAllEbooks().size());
        model.addAttribute("publisherCount", publisherService.getAllPublishers().size());
        model.addAttribute("licenseCount", licenseService.getAllLicenses().size());
        return "index";
    }

    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("member", new Member());
        return "members";
    }

    @GetMapping("/members/new")
    public String memberForm(Model model) {
        model.addAttribute("member", new Member());
        return "member-form";
    }

    @GetMapping("/members/edit/{id}")
    public String editMember(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.getMemberById(id));
        return "member-form";
    }

    @PostMapping("/members/save")
    public String saveMember(@ModelAttribute Member member, @RequestParam(required = false) Long id) {
        if (id == null) {
            memberService.addMember(member);
        } else {
            memberService.updateMember(id, member);
        }
        return "redirect:/members";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/new")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product, @RequestParam(required = false) Long id) {
        if (id == null) {
            productService.addProduct(product);
        } else {
            productService.updateProduct(id, product);
        }
        return "redirect:/products";
    }

    @GetMapping("/publishers")
    public String publishers(Model model) {
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("publisher", new Publisher());
        return "publishers";
    }

    @GetMapping("/publishers/new")
    public String publisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher-form";
    }

    @GetMapping("/publishers/edit/{id}")
    public String editPublisher(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherService.getPublisherById(id));
        return "publisher-form";
    }

    @PostMapping("/publishers/save")
    public String savePublisher(@ModelAttribute Publisher publisher, @RequestParam(required = false) Long id) {
        if (id == null) {
            publisherService.addPublisher(publisher);
        } else {
            publisherService.updatePublisher(id, publisher);
        }
        return "redirect:/publishers";
    }

    @GetMapping("/purchase-orders")
    public String purchaseOrders(Model model) {
        model.addAttribute("purchaseOrders", purchaseOrderService.getAllPurchaseOrders());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "purchase-orders";
    }

    @GetMapping("/purchase-orders/new")
    public String purchaseOrderForm(Model model) {
        model.addAttribute("purchaseOrder", new PurchaseOrder());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "purchase-order-form";
    }

    @GetMapping("/purchase-orders/edit/{id}")
    public String editPurchaseOrder(@PathVariable Long id, Model model) {
        model.addAttribute("purchaseOrder", purchaseOrderService.getPurchaseOrderById(id));
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "purchase-order-form";
    }

    @PostMapping("/purchase-orders/save")
        public String savePurchaseOrder(
            @ModelAttribute PurchaseOrder purchaseOrder,
            @RequestParam(required = false) Long id,
            @RequestParam Long publisherId) {
        Publisher publisher = publisherService.getPublisherById(publisherId);
        if (publisher != null) {
            purchaseOrder.setPublisher(publisher);
        }
        if (id == null) {
            purchaseOrderService.addPurchaseOrder(purchaseOrder);
        } else {
            purchaseOrderService.updatePurchaseOrder(id, purchaseOrder);
        }
        return "redirect:/purchase-orders";
    }

    @GetMapping("/sales-orders")
    public String salesOrders(Model model) {
        model.addAttribute("salesOrders", salesOrderService.getAllSalesOrders());
        model.addAttribute("schoolDistricts", schoolDistrictService.getAllSchoolDistricts());
        model.addAttribute("products", productService.getAllProducts());
        return "sales-orders";
    }

    @GetMapping("/sales-orders/new")
    public String salesOrderForm(Model model) {
        model.addAttribute("salesOrder", new SalesOrder());
        model.addAttribute("schoolDistricts", schoolDistrictService.getAllSchoolDistricts());
        model.addAttribute("products", productService.getAllProducts());
        return "sales-order-form";
    }

    @GetMapping("/sales-orders/edit/{id}")
    public String editSalesOrder(@PathVariable Long id, Model model) {
        model.addAttribute("salesOrder", salesOrderService.getSalesOrderById(id));
        model.addAttribute("schoolDistricts", schoolDistrictService.getAllSchoolDistricts());
        model.addAttribute("products", productService.getAllProducts());
        return "sales-order-form";
    }

    @PostMapping("/sales-orders/save")
        public String saveSalesOrder(
            @ModelAttribute SalesOrder salesOrder,
            @RequestParam(required = false) Long id,
            @RequestParam Long schoolDistrictId,
            @RequestParam Long productId) {
        SchoolDistrict schoolDistrict = schoolDistrictService.getSchoolDistrictById(schoolDistrictId);
        Product product = productService.getProductById(productId);
        if (schoolDistrict != null) {
            salesOrder.setSchoolDistrict(schoolDistrict);
        }
        if (product != null) {
            salesOrder.setProduct(product);
        }
        if (id == null) {
            salesOrderService.addSalesOrder(salesOrder);
        } else {
            salesOrderService.updateSalesOrder(id, salesOrder);
        }
        return "redirect:/sales-orders";
    }

    @GetMapping("/school-districts")
    public String schoolDistricts(Model model) {
        model.addAttribute("schoolDistricts", schoolDistrictService.getAllSchoolDistricts());
        return "school-districts";
    }

    @GetMapping("/school-districts/new")
    public String schoolDistrictForm(Model model) {
        model.addAttribute("schoolDistrict", new SchoolDistrict());
        return "school-district-form";
    }

    @GetMapping("/school-districts/edit/{id}")
    public String editSchoolDistrict(@PathVariable Long id, Model model) {
        model.addAttribute("schoolDistrict", schoolDistrictService.getSchoolDistrictById(id));
        return "school-district-form";
    }

    @PostMapping("/school-districts/save")
    public String saveSchoolDistrict(
            @ModelAttribute SchoolDistrict schoolDistrict,
            @RequestParam(required = false) Long id) {
        if (id == null) {
            schoolDistrictService.addSchoolDistrict(schoolDistrict);
        } else {
            schoolDistrictService.updateSchoolDistrict(id, schoolDistrict);
        }
        return "redirect:/school-districts";
    }

    @GetMapping("/vendor-bills")
    public String vendorBills(Model model) {
        model.addAttribute("vendorBills", vendorBillService.getAllVendorBills());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("purchaseOrders", purchaseOrderService.getAllPurchaseOrders());
        return "vendor-bills";
    }

    @GetMapping("/vendor-bills/new")
    public String vendorBillForm(Model model) {
        model.addAttribute("vendorBill", new VendorBill());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("purchaseOrders", purchaseOrderService.getAllPurchaseOrders());
        return "vendor-bill-form";
    }

    @GetMapping("/vendor-bills/edit/{id}")
    public String editVendorBill(@PathVariable Long id, Model model) {
        model.addAttribute("vendorBill", vendorBillService.getVendorBillById(id));
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("purchaseOrders", purchaseOrderService.getAllPurchaseOrders());
        return "vendor-bill-form";
    }

    @PostMapping("/vendor-bills/save")
        public String saveVendorBill(
            @ModelAttribute VendorBill vendorBill,
            @RequestParam(required = false) Long id,
            @RequestParam Long publisherId,
            @RequestParam Long purchaseOrderId) {
        Publisher publisher = publisherService.getPublisherById(publisherId);
        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(purchaseOrderId);
        if (publisher != null) {
            vendorBill.setPublisher(publisher);
        }
        if (purchaseOrder != null) {
            vendorBill.setPurchaseOrder(purchaseOrder);
        }
        if (id == null) {
            vendorBillService.addVendorBill(vendorBill);
        } else {
            vendorBillService.updateVendorBill(id, vendorBill);
        }
        return "redirect:/vendor-bills";
    }

    @GetMapping("/ebooks")
    public String ebooks(Model model) {
        model.addAttribute("ebooks", ebookService.getAllEbooks());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("ebook", new Ebook());
        return "ebooks";
    }

    @GetMapping("/ebooks/new")
    public String ebookForm(Model model) {
        model.addAttribute("ebook", new Ebook());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "ebook-form";
    }

    @GetMapping("/ebooks/edit/{id}")
    public String editEbook(@PathVariable Long id, Model model) {
        model.addAttribute("ebook", ebookService.getEbookById(id));
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "ebook-form";
    }

    @PostMapping("/ebooks/save")
        public String saveEbook(
            @ModelAttribute Ebook ebook,
            @RequestParam(required = false) Long id,
            @RequestParam Long publisherId) {
        Publisher publisher = publisherService.getPublisherById(publisherId);
        if (publisher != null) {
            ebook.setPublisher(publisher);
        }
        if (id == null) {
            ebookService.addEbook(ebook);
        } else {
            ebookService.updateEbook(id, ebook);
        }
        return "redirect:/ebooks";
    }

    @GetMapping("/licenses")
    public String licenses(Model model) {
        model.addAttribute("licenses", licenseService.getAllLicenses());
        model.addAttribute("ebooks", ebookService.getAllEbooks());
        return "licenses";
    }

    @GetMapping("/licenses/new")
    public String licenseForm(Model model) {
        model.addAttribute("license", new License());
        model.addAttribute("ebooks", ebookService.getAllEbooks());
        return "license-form";
    }

    @GetMapping("/licenses/edit/{id}")
    public String editLicense(@PathVariable Long id, Model model) {
        model.addAttribute("license", licenseService.getLicenseById(id));
        model.addAttribute("ebooks", ebookService.getAllEbooks());
        return "license-form";
    }

    @PostMapping("/licenses/save")
        public String saveLicense(
            @ModelAttribute License license,
            @RequestParam(required = false) Long id,
            @RequestParam Long ebookId) {
        Ebook ebook = ebookService.getEbookById(ebookId);
        if (ebook != null) {
            license.setEbook(ebook);
        }
        if (id == null) {
            licenseService.addLicense(license);
        } else {
            licenseService.updateLicense(id, license);
        }
        return "redirect:/licenses";
    }

    @PostMapping("/{module:members|products|publishers|purchase-orders|sales-orders|school-districts|vendor-bills|ebooks|licenses}/delete/{id}")
    public String deleteRecord(
            @PathVariable String module,
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            switch (module) {
                case "members" -> memberService.deleteMember(id);
                case "products" -> productService.deleteProduct(id);
                case "publishers" -> publisherService.deletePublisher(id);
                case "purchase-orders" -> purchaseOrderService.deletePurchaseOrder(id);
                case "sales-orders" -> salesOrderService.deleteSalesOrder(id);
                case "school-districts" -> schoolDistrictService.deleteSchoolDistrict(id);
                case "vendor-bills" -> vendorBillService.deleteVendorBill(id);
                case "ebooks" -> ebookService.deleteEbook(id);
                case "licenses" -> licenseService.deleteLicense(id);
                default -> throw new IllegalArgumentException("Unknown module: " + module);
            }
            redirectAttributes.addFlashAttribute("message", "Record deleted.");
        } catch (DataIntegrityViolationException exception) {
            redirectAttributes.addFlashAttribute("error", "This record is in use and cannot be deleted.");
        }
        return "redirect:/" + module;
    }
}
