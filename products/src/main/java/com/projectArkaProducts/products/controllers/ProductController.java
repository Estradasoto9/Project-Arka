package com.projectArkaProducts.products.controllers;

import com.projectArkaProducts.products.dtos.CreateProductDto;
import com.projectArkaProducts.products.dtos.UpdateProductDto;
import com.projectArkaProducts.products.entities.Product;
import com.projectArkaProducts.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody CreateProductDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody UpdateProductDto productDto) {
        return ResponseEntity.ok(productService.update(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // get products for name or description
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String termino) {
        return productService.searchProductsByNameOrDescription(termino);
    }

    // get all products in order
    @GetMapping("/sorted")
    public List<Product> getAllProductsSorted() {
        return productService.getAllProductsSorted();
    }

    // get product by price range
    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return productService.getProductsByPriceRange(min, max);
    }
}