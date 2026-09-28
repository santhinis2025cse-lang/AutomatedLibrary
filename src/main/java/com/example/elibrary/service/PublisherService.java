package com.example.elibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.elibrary.model.Publisher;
import com.example.elibrary.repository.PublisherRepository;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher addPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher updatePublisher(Long id, Publisher updatedPublisher) {
        Publisher existing = publisherRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setName(updatedPublisher.getName());
        existing.setEmail(updatedPublisher.getEmail());
        existing.setPhone(updatedPublisher.getPhone());
        if (updatedPublisher.getAddress() != null) {
            existing.setAddress(updatedPublisher.getAddress());
        }
        return publisherRepository.save(existing);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}