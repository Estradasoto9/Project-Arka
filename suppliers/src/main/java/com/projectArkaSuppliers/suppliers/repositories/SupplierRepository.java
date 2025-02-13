package com.projectArkaSuppliers.suppliers.repositories;


import com.projectArkaSuppliers.suppliers.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByActive(boolean active);
}
