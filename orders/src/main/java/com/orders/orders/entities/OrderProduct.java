package com.orders.orders.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id = new OrderProductId();

}
