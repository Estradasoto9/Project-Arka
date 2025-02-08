package com.AdrianAlejandroEstradaLibreria.Libreria.services;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Genero;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> getAll() {
        return generoRepository.findAll();
    }

    public Genero getById(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
    }

    public Genero saveGenero(Genero genero) {
        genero.setCreatedAt(LocalDateTime.now());
        genero.setUpdatedAt(LocalDateTime.now());
        return generoRepository.save(genero);
    }

    public void deleteGenero(Long id) {
        if (!generoRepository.existsById(id)) {
            throw new RuntimeException("Género no encontrado");
        }
        generoRepository.deleteById(id);
    }
}
