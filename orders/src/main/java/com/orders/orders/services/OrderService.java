package com.orders.orders.services;

import com.orders.orders.dtos.OrderDTO;
import com.orders.orders.entities.Client;
import com.orders.orders.entities.Order;
import com.orders.orders.entities.Product;
import com.orders.orders.repositories.ClientRepository;
import com.orders.orders.repositories.OrderRepository;
import com.orders.orders.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(OrderDTO orderDTO) {
        Client client = clientRepository.findById(orderDTO.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<Product> products = productRepository.findAllById(orderDTO.getProductIds());
        if (products.isEmpty()) {
            throw new RuntimeException("No se encontraron productos válidos");
        }

        BigDecimal total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setClient(client);
        order.setProducts(products);
        order.setOrderDate(LocalDateTime.now());
        order.setTotal(total);

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByProduct(Long productId) { return orderRepository.findByProducts_Id(productId); }

    public List<Order> getOrdersByDateRange(LocalDateTime start, LocalDateTime end) { return orderRepository.findByOrderDateBetween(start, end); }

    public List<Order> getOrdersByClient(Long clientId) { return orderRepository.findByClient_Id(clientId); }

    public void deleteOrder(Long id) { orderRepository.deleteById(id); }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
