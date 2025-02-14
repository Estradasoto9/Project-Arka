package com.projectArkaSuppliers.suppliers.services;

import com.projectArkaSuppliers.suppliers.dtos.StoreDto;
import com.projectArkaSuppliers.suppliers.entities.Address;
import com.projectArkaSuppliers.suppliers.entities.Store;
import com.projectArkaSuppliers.suppliers.entities.Supplier;
import com.projectArkaSuppliers.suppliers.repositories.StoreRepository;
import com.projectArkaSuppliers.suppliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private SupplierRepository supplierRepository; // Inyectar el repositorio del proveedor

    public Store create(StoreDto storeDto, Long supplierId) {
        // Buscar el proveedor por ID
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (optionalSupplier.isEmpty()) {
            throw new RuntimeException("Supplier not found with ID: " + supplierId);
        }

        Store store = new Store();
        store.setSupplier(optionalSupplier.get());
        store.setName(storeDto.getName());

        Address addressNew = new Address();
        addressNew.setCountry("Chile");
        addressNew.setStreet("Av 25");
        addressNew.setCity("Santigo de Chile");

        store.setAddress(addressNew);
        return storeRepository.save(store);
    }

    public Store update(StoreDto dto, Long supplierId, Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);

        if (storeOptional.isEmpty()){
            throw new RuntimeException("Store not found with ID: " + id);
        }

        if (supplierOptional.isEmpty()){
            throw new RuntimeException("Supplier not found with ID: " + supplierId);
        }

        Store store = storeOptional.get();
        store.setName(dto.getName());

        return storeRepository.save(store);
    }
}