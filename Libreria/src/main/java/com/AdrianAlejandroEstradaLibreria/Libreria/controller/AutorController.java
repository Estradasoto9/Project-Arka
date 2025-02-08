package com.AdrianAlejandroEstradaLibreria.Libreria.controller;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Autor;
import com.AdrianAlejandroEstradaLibreria.Libreria.services.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<Autor> listarAutores() {
        return autorService.getAll();
    }

    @PostMapping
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
        if (autor.getNombre() == null || autor.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Validación básica
        }
        return ResponseEntity.ok(autorService.saveAutor(autor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutor(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(autorService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Long id, @RequestBody Autor autor) {
        try {
            Autor autorExistente = autorService.getById(id);
            autorExistente.setNombre(autor.getNombre());
            return ResponseEntity.ok(autorService.saveAutor(autorExistente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        try {
            autorService.deleteAutor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
