package com.orders.orders.repositories;

import com.orders.orders.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCustomerId(Long customerId);
    List<Cart> findByProductsIsEmpty();
}