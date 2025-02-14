package com.projectArkaSuppliers.suppliers.controllers;

import com.projectArkaSuppliers.suppliers.dtos.CreateSupplierDto;
import com.projectArkaSuppliers.suppliers.dtos.UpdateSupplierDto;
import com.projectArkaSuppliers.suppliers.entities.Supplier;
import com.projectArkaSuppliers.suppliers.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Crear un nuevo proveedor
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody CreateSupplierDto createSupplierDto) {
        Supplier newSupplier = supplierService.create(createSupplierDto);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAll();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierService.get(id);
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSupplierDto updateSupplierDto) {
        Supplier updatedSupplier = supplierService.update(id, updateSupplierDto);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    // Eliminar un proveedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
