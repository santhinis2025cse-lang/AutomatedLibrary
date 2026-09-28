package com.example.elibrary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elibrary.model.Publisher;
import com.example.elibrary.service.PublisherService;

@RestController
@RequestMapping("/api/elibrary")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/publishers")
    public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.addPublisher(publisher));
    }

    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/publishers/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        Publisher publisher = publisherService.getPublisherById(id);
        if (publisher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/publishers/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
        Publisher updated = publisherService.updatePublisher(id, publisher);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/publishers/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        if (publisherService.getPublisherById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}