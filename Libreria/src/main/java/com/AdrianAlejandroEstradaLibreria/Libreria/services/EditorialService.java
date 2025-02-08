package com.AdrianAlejandroEstradaLibreria.Libreria.services;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Editorial;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {
    @Autowired
    private EditorialRepository editorialRepository;

    public List<Editorial> getAll() {
        return editorialRepository.findAll();
    }

    public Editorial getById(Long id) {
        return editorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));
    }

    public Editorial saveEditorial(Editorial editorial) {
        editorial.setCreatedAt(LocalDateTime.now());
        editorial.setUpdatedAt(LocalDateTime.now());
        return editorialRepository.save(editorial);
    }

    public void deleteEditorial(Long id) {
        if (!editorialRepository.existsById(id)) {
            throw new RuntimeException("Editorial no encontrada");
        }
        editorialRepository.deleteById(id);
    }
}
