package com.AdrianAlejandroEstradaLibreria.Libreria.services;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Autor;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    public Autor getById(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));
    }

    public List<Autor> getByName(String nombre) {
        return autorRepository.findByNombreContaining(nombre);
    }

    public Autor saveAutor(Autor autor) {
        autor.setCreatedAt(LocalDateTime.now());
        autor.setUpdatedAt(LocalDateTime.now());
        if (autor.getNombre() == null || autor.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del autor no puede estar vacío");
        }
        return autorRepository.save(autor);
    }

    public void deleteAutor(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, el autor con ID " + id + " no existe.");
        }
        autorRepository.deleteById(id);
    }
}
