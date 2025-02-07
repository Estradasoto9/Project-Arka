package com.projectArkaProducts.products.services;

import com.projectArkaProducts.products.dtos.CreateProductDto;
import com.projectArkaProducts.products.dtos.UpdateProductDto;
import com.projectArkaProducts.products.entities.Product;
import com.projectArkaProducts.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(CreateProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product update(Long id, UpdateProductDto productDto) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setStock(productDto.getStock());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    // get products for name or description
    public List<Product> searchProductsByNameOrDescription(String term) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(term, term);
    }

    // get all products in order
    public List<Product> getAllProductsSorted() {
        return productRepository.findAllByOrderByNameAsc();
    }

    // get product by price range
    public List<Product> getProductsByPriceRange(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max);
    }
}