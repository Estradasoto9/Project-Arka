package com.AdrianAlejandroEstradaLibreria.Libreria.repositories;

import com.AdrianAlejandroEstradaLibreria.Libreria.entiities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
}

