package com.AdrianAlejandroEstradaLibreria.Libreria.controller;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Editorial;
import com.AdrianAlejandroEstradaLibreria.Libreria.services.EditorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    private final EditorialService editorialService;

    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping
    public List<Editorial> listarEditoriales() {
        return editorialService.getAll();
    }

    @PostMapping
    public ResponseEntity<Editorial> createEditorial(@RequestBody Editorial editorial) {
        if (editorial.getNombre() == null || editorial.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Validación básica
        }
        return ResponseEntity.ok(editorialService.saveEditorial(editorial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorial> getEditorial(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(editorialService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> updateEditorial(@PathVariable Long id, @RequestBody Editorial editorial) {
        try {
            Editorial editorialExistente = editorialService.getById(id);
            editorialExistente.setNombre(editorial.getNombre());
            return ResponseEntity.ok(editorialService.saveEditorial(editorialExistente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditorial(@PathVariable Long id) {
        try {
            editorialService.deleteEditorial(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
