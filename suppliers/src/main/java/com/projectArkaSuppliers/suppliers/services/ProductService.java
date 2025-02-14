package com.projectArkaSuppliers.suppliers.services;

import com.projectArkaSuppliers.suppliers.dtos.ProductDto;
import com.projectArkaSuppliers.suppliers.entities.Product;
import com.projectArkaSuppliers.suppliers.entities.Supplier;
import com.projectArkaSuppliers.suppliers.repositories.ProductRepository;
import com.projectArkaSuppliers.suppliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Metodo para agregar un nuevo producto
    public Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPurchasePrice(productDto.getPurchasePrice());
        product.setSalePrice(productDto.getSalePrice());
        product.setStock(0);
        return productRepository.save(product);
    }

    // Metodo para obtener todos los proveedores asociados a un producto específico
    public Set<Supplier> getSuppliersByProductId(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.map(Product::getSuppliers).orElse(Set.of());
    }


}
