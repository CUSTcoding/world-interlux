package com.worldinterlux.ecommerce.orders.controller;

import com.worldinterlux.ecommerce.orders.dto.OrderDTO;
import com.worldinterlux.ecommerce.orders.entity.Order;
import com.worldinterlux.ecommerce.orders.enums.OrderStatus;
import com.worldinterlux.ecommerce.orders.service.OrderItemService;
import com.worldinterlux.ecommerce.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequest request) {
        OrderDTO orderDTO = orderService.createOrder(request);
        return ResponseEntity.ok(orderDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable UUID id) {
        return orderService.getOrderById(id)
                .map(OrderDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable UUID userId) {
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId)
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        OrderStatus orderStatus;
        try {
            orderStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        Order updatedOrder = orderService.updateOrderStatus(id, orderStatus);
        return ResponseEntity.ok(new OrderDTO(updatedOrder));
    }


    @PatchMapping("/{id}/pay")
    public ResponseEntity<OrderDTO> payOrder(@PathVariable UUID id, @RequestParam BigDecimal amount) {
        Order updatedOrder = orderService.updatePaidAmount(id, amount);
        return ResponseEntity.ok(new OrderDTO(updatedOrder));
    }
}

