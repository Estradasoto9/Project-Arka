package com.projectArkaSuppliers.suppliers.repositories;


import com.projectArkaSuppliers.suppliers.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
