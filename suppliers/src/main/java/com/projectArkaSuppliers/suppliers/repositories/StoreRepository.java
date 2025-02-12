package com.projectArkaSuppliers.suppliers.repositories;

import com.projectArkaSuppliers.suppliers.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}