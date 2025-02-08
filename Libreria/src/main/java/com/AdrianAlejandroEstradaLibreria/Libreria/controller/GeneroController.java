package com.AdrianAlejandroEstradaLibreria.Libreria.controller;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Genero;
import com.AdrianAlejandroEstradaLibreria.Libreria.services.GeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public List<Genero> listarGeneros() {
        return generoService.getAll();
    }

    @PostMapping
    public ResponseEntity<Genero> createGenero(@RequestBody Genero genero) {
        if (genero.getNombre() == null || genero.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Validación básica
        }
        return ResponseEntity.ok(generoService.saveGenero(genero));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> getGenero(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(generoService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> updateGenero(@PathVariable Long id, @RequestBody Genero genero) {
        try {
            Genero generoExistente = generoService.getById(id);
            generoExistente.setNombre(genero.getNombre());
            return ResponseEntity.ok(generoService.saveGenero(generoExistente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenero(@PathVariable Long id) {
        try {
            generoService.deleteGenero(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
