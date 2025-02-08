package com.AdrianAlejandroEstradaLibreria.Libreria.services;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Autor;
import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Editorial;
import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Genero;
import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Libro;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.AutorRepository;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.EditorialRepository;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.GeneroRepository;
import com.AdrianAlejandroEstradaLibreria.Libreria.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private GeneroRepository generoRepository;

    // Obtener todos los libros
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    // Obtener un libro por ID
    public Optional<Libro> getLibroById(Long id) {
        return libroRepository.findById(id);
    }

    // Crear un libro
    public Libro saveLibro(Libro libro) {
        if (libro.getAutor() == null || libro.getAutor().getId() == null) {
            throw new IllegalArgumentException("El autor no puede ser nulo.");
        }

        if (libro.getEditorial() == null || libro.getEditorial().getId() == null) {
            throw new IllegalArgumentException("La editorial no puede ser nula.");
        }

        if (libro.getGeneros() == null || libro.getGeneros().isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un género.");
        }

        Autor autor = autorRepository.findById(libro.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Editorial editorial = editorialRepository.findById(libro.getEditorial().getId())
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));

        Set<Genero> generosEncontrados = libro.getGeneros().stream()
                .map(g -> generoRepository.findById(g.getId())
                        .orElseThrow(() -> new RuntimeException("Género con ID " + g.getId() + " no encontrado")))
                .collect(Collectors.toSet());

        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setGeneros(generosEncontrados);

        return libroRepository.save(libro);
    }

    // Actualizar un libro
    public Optional<Libro> updateLibro(Long id, Libro libroDetails) {
        return libroRepository.findById(id).map(libro -> {
            libro.setAutor(libroDetails.getAutor());
            libro.setEditorial(libroDetails.getEditorial());
            return libroRepository.save(libro);
        });
    }

    // Eliminar un libro
    public boolean deleteLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener libros por autor
    public List<Libro> getLibrosByAutor(Long idAutor) {
        return libroRepository.findByAutor_Id(idAutor);
    }

    // Obtener libros por editorial
    public List<Libro> getLibrosByEditorial(Long idEditorial) {
        return libroRepository.findByEditorial_Id(idEditorial);
    }

    // Buscar libros por título, autor o género
    public List<Libro> searchLibros(String termino) {
        return libroRepository.findByTituloContaining(termino);
    }

    // Asignar un género a un libro
    public boolean assignGeneroToLibro(Long idLibro, Long idGenero) {

        return true;
    }

    // Eliminar un género de un libro sin eliminar el libro
    public boolean removeGeneroFromLibro(Long idLibro, Long idGenero) {
        return true;
    }
}