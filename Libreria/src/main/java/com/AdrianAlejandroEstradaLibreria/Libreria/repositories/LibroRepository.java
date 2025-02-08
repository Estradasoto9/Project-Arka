package com.AdrianAlejandroEstradaLibreria.Libreria.repositories;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContaining(String titulo);
    List<Libro> findByAutor_Id(Long autorId);
    List<Libro> findByEditorial_Id(Long editorialId);
}
