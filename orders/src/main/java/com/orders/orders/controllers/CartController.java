package com.orders.orders.controllers;

import com.orders.orders.entities.Cart;
import com.orders.orders.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Cart> getCartByCustomer(@PathVariable Long customerId) {
        return cartService.getCartByCustomer(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-product")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Long customerId, @RequestParam Long productId) {
        return ResponseEntity.ok(cartService.addProductToCart(customerId, productId));
    }

    @DeleteMapping("/clear/{customerId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long customerId) {
        cartService.clearCart(customerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/abandoned")
    public ResponseEntity<List<Cart>> getAbandonedCarts() {
        return ResponseEntity.ok(cartService.getAbandonedCarts());
    }
}

