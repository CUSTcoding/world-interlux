package com.worldinterlux.ecommerce.orders.service;

import com.worldinterlux.ecommerce.orders.controller.CreateOrderRequest;
import com.worldinterlux.ecommerce.orders.dto.OrderDTO;
import com.worldinterlux.ecommerce.orders.entity.Order;
import com.worldinterlux.ecommerce.orders.entity.OrderItem;
import com.worldinterlux.ecommerce.orders.enums.OrderStatus;
import com.worldinterlux.ecommerce.orders.repository.OrderRepository;
import com.worldinterlux.ecommerce.orders.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderDTO createOrder(CreateOrderRequest request) {
        // Criar a entidade Order
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setTotalAmount(request.getTotalAmount());
        order.setStatus(OrderStatus.PENDING);

        // Salvar order primeiro para gerar ID
        Order savedOrder = orderRepository.save(order);

        // Mapear e salvar os itens
        List<OrderItem> items = request.getItems().stream()
                .map(i -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(savedOrder);
                    item.setProductId(i.getProductId());
                    item.setQuantity(i.getQuantity());
                    item.setPrice(i.getPrice());
                    return item;
                })
                .collect(Collectors.toList());

        orderItemRepository.saveAll(items);

        savedOrder.setItems(items);

        return new OrderDTO(savedOrder);
    }

    public java.util.Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(UUID id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order updatePaidAmount(UUID id, java.math.BigDecimal amount) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setPaidAmount(order.getPaidAmount().add(amount));
        return orderRepository.save(order);
    }
}
