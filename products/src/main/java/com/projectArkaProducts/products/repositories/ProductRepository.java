package com.projectArkaProducts.products.repositories;

import com.projectArkaProducts.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
