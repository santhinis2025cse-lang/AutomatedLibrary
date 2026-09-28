package com.example.elibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.elibrary.model.Ebook;
import com.example.elibrary.repository.EbookRepository;

@Service
public class EbookService {

    private final EbookRepository ebookRepository;

    public EbookService(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    public Ebook addEbook(Ebook ebook) {
        return ebookRepository.save(ebook);
    }

    public List<Ebook> getAllEbooks() {
        return ebookRepository.findAll();
    }

    public Ebook getEbookById(Long id) {
        return ebookRepository.findById(id).orElse(null);
    }

    public Ebook updateEbook(Long id, Ebook ebook) {

        Ebook existingEbook = ebookRepository.findById(id).orElse(null);

        if (existingEbook == null) {
            return null;
        }

        existingEbook.setTitle(ebook.getTitle());
        existingEbook.setIsbn(ebook.getIsbn());
        existingEbook.setGenre(ebook.getGenre());
        existingEbook.setPrice(ebook.getPrice());
        existingEbook.setPublisher(ebook.getPublisher());

        return ebookRepository.save(existingEbook);
    }

    public void deleteEbook(Long id) {
        ebookRepository.deleteById(id);
    }
}