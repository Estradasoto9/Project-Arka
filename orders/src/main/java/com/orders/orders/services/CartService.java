package com.orders.orders.services;

import com.orders.orders.entities.Cart;
import com.orders.orders.repositories.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<Cart> getCartByCustomer(Long customerId) {
        return Optional.ofNullable(cartRepository.findByCustomerId(customerId));
    }

    @Transactional
    public Cart addProductToCart(Long customerId, Long productId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart == null) {
            cart = new Cart();
            cart.setCustomerId(customerId);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart != null) {
            cart.getProducts().clear();
            cartRepository.save(cart);
        }
    }

    public List<Cart> getAbandonedCarts() {
        return cartRepository.findByProductsIsEmpty();
    }
}