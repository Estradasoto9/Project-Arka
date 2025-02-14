package com.orders.orders.services;

import com.orders.orders.entities.Cart;
import com.orders.orders.entities.Client;
import com.orders.orders.entities.Product;
import com.orders.orders.repositories.CartRepository;

import com.orders.orders.repositories.ProductRepository;

import com.projectArkaProducts.products.entities.Product;
import com.projectArkaProducts.products.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired

    private final CartRepository cartRepository;
  
    private  CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Optional<Cart> getCartByClient(Client client) {
        return cartRepository.findByClient(client);
    }

    @Transactional
    public Cart addProductToCart(Client client, Long productId) {
        Cart cart = cartRepository.findByClient(client).orElse(new Cart());
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(Client client) {
        Cart cart = cartRepository.findByClient(client).orElse(null);
        if (cart != null) {
            cart.getProducts().clear();
            cartRepository.save(cart);
        }
    }

    public List<Cart> getAbandonedCarts() {
        return cartRepository.findAllByProductsIsEmpty();
    }
}