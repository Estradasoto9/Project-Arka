package com.projectArkaProducts.products.repositories;

import com.projectArkaProducts.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

    List<Product> findAllByOrderByNameAsc();

    List<Product> findByPriceBetween(Double min, Double max);
}

