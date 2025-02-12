package com.projectArkaSuppliers.suppliers.controllers;

import com.projectArkaSuppliers.suppliers.dtos.ProductDto;
import com.projectArkaSuppliers.suppliers.entities.Product;
import com.projectArkaSuppliers.suppliers.entities.Supplier;
import com.projectArkaSuppliers.suppliers.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint para agregar un nuevo producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product newProduct = productService.addProduct(productDto);
        return ResponseEntity.ok(newProduct);
    }

    // Endpoint para obtener proveedores por ID de producto
    @GetMapping("/{id}/suppliers")
    public ResponseEntity<Set<Supplier>> getSuppliers(@PathVariable Long id) {
        Set<Supplier> suppliers = productService.getSuppliersByProductId(id);
        return ResponseEntity.ok(suppliers);
    }


}
