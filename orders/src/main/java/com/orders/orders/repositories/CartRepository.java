package com.orders.orders.repositories;

import com.orders.orders.entities.Cart;
import com.orders.orders.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByClient(Client client);
    List<Cart> findAllByProductsIsEmpty();
}