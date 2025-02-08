package com.AdrianAlejandroEstradaLibreria.Libreria.repositories;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreContaining(String nombre);
}
