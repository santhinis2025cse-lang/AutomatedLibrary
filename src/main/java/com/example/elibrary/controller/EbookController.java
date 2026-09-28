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

import com.example.elibrary.model.Ebook;
import com.example.elibrary.service.EbookService;

@RestController
@RequestMapping("/api/elibrary/ebooks")
public class EbookController {

    private final EbookService ebookService;

    public EbookController(EbookService ebookService) {
        this.ebookService = ebookService;
    }

    @PostMapping
    public Ebook addEbook(@RequestBody Ebook ebook) {
        return ebookService.addEbook(ebook);
    }

    @GetMapping
    public List<Ebook> getAllEbooks() {
        return ebookService.getAllEbooks();
    }

    @GetMapping("/{id}")
    public Ebook getEbookById(@PathVariable Long id) {
        return ebookService.getEbookById(id);
    }

    @PutMapping("/{id}")
    public Ebook updateEbook(
            @PathVariable Long id,
            @RequestBody Ebook ebook) {

        return ebookService.updateEbook(id, ebook);
    }

    @DeleteMapping("/{id}")
    public void deleteEbook(@PathVariable Long id) {
        ebookService.deleteEbook(id);
    }
}