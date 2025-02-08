package com.AdrianAlejandroEstradaLibreria.Libreria.controller;

import com.AdrianAlejandroEstradaLibreria.Libreria.dtos.LibroDTO;
import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Autor;
import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Editorial;
import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Genero;
import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Libro;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.AutorRepository;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.EditorialRepository;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.GeneroRepository;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.LibroRepository;
import com.AdrianAlejandroEstradaLibreria.Libreria.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LibroController {

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private GeneroRepository generoRepository;

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // Mostrar todos los libros
    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> getAllLibros() {
        return ResponseEntity.ok(libroService.getAllLibros());
    }

    // Mostrar un libro por ID
    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Optional<Libro> libro = libroService.getLibroById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un libro
    @PostMapping("/libros")
    public ResponseEntity<?> crearLibro(@RequestBody LibroDTO libroDTO) {
        // Buscar el autor en la base de datos
        Autor autor = autorRepository.findById(libroDTO.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        // Buscar la editorial en la base de datos
        Editorial editorial = editorialRepository.findById(libroDTO.getEditorial().getId())
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));

        // Crear la instancia del libro
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitulo());
        libro.setDescripcion(libroDTO.getDescripcion());
        libro.setAutor(autor);  // Se asigna el autor recuperado
        libro.setEditorial(editorial);  // Se asigna la editorial recuperada

        // Procesar géneros
        Set<Genero> generos = libroDTO.getGeneros().stream()
                .map(g -> new Genero(g.getId())) // Solo se asigna el ID
                .collect(Collectors.toSet());
        libro.setGeneros(generos);

        // Guardar el libro en la base de datos
        Libro nuevoLibro = libroService.saveLibro(libro);
        return ResponseEntity.ok(nuevoLibro);
    }

    // Actualizar un libro
    @PutMapping("/libros/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        Optional<Libro> updatedLibro = libroService.updateLibro(id, libroDetails);
        return updatedLibro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Borrar un libro
    @DeleteMapping("/libros/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        boolean isDeleted = libroService.deleteLibro(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Mostrar todos los libros que pertenecen a un autor
    @GetMapping("/autores/{idAutor}/libros")
    public ResponseEntity<List<Libro>> getLibrosByAutor(@PathVariable Long idAutor) {
        return ResponseEntity.ok(libroService.getLibrosByAutor(idAutor));
    }

    // Mostrar todos los libros de una editorial
    @GetMapping("/editoriales/{idEditorial}/libros")
    public ResponseEntity<List<Libro>> getLibrosByEditorial(@PathVariable Long idEditorial) {
        return ResponseEntity.ok(libroService.getLibrosByEditorial(idEditorial));
    }

    // BONUS: Asignar un género a un libro existente
    @PostMapping("/libros/{idLibro}/generos/{idGenero}")
    public ResponseEntity<Void> assignGeneroToLibro(@PathVariable Long idLibro, @PathVariable Long idGenero) {
        boolean isAssigned = libroService.assignGeneroToLibro(idLibro, idGenero);
        return isAssigned ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    // BONUS: Eliminar un género de un libro (sin eliminar el libro)
    @DeleteMapping("/libros/{idLibro}/generos/{idGenero}")
    public ResponseEntity<Void> removeGeneroFromLibro(@PathVariable Long idLibro, @PathVariable Long idGenero) {
        boolean isRemoved = libroService.removeGeneroFromLibro(idLibro, idGenero);
        return isRemoved ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}
