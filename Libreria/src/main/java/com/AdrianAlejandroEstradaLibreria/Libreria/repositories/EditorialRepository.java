package com.AdrianAlejandroEstradaLibreria.Libreria.repositories;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {
    List<Editorial> findByNombreContaining(String nombre);
}
