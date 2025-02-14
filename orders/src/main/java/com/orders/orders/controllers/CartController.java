package com.orders.orders.controllers;

import com.orders.orders.entities.Cart;
import com.orders.orders.entities.Client;
import com.orders.orders.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Cart> getCartByClient(@PathVariable Client client) {
        return cartService.getCartByClient(client)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-product")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Client client, @RequestParam Long productId) {
        return ResponseEntity.ok(cartService.addProductToCart(client, productId));
    }

    @DeleteMapping("/clear/{clientId}")
    public ResponseEntity<Void> clearCart(@PathVariable Client client) {
        cartService.clearCart(client);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/abandoned")
    public ResponseEntity<List<Cart>> getAbandonedCarts() {
        return ResponseEntity.ok(cartService.getAbandonedCarts());
    }
}


