package com.projectArkaSuppliers.suppliers.controllers;

import com.projectArkaSuppliers.suppliers.dtos.StoreDto;
import com.projectArkaSuppliers.suppliers.entities.Store;
import com.projectArkaSuppliers.suppliers.services.StoreService;
import org.springframework.beans.factory.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers/{idSupplier}/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // Crear una nueva tienda
    @PostMapping
    public ResponseEntity<Store> createStore(
            @PathVariable("idSupplier") Long idSupplier, // Captura el ID del proveedor desde la URL
            @Valid @RequestBody StoreDto storeDto) {
        Store newStore = storeService.create(storeDto, idSupplier); // Pasa el ID del proveedor al servicio
        return new ResponseEntity<>(newStore, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> update( @PathVariable("idSupplier") Long idSupplier,
                                         @PathVariable("id") Long id,
                                         @Valid @RequestBody StoreDto storeDto){

        Store updatedStore = storeService.update(storeDto, idSupplier, id);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }
}
