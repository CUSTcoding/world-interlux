package com.worldinterlux.ecommerce.orders.controller;

import com.worldinterlux.ecommerce.orders.dto.OrderItemDTO;
import com.worldinterlux.ecommerce.orders.entity.OrderItem;
import com.worldinterlux.ecommerce.orders.service.OrderItemService;
import com.worldinterlux.ecommerce.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;

    public OrderItemController(OrderItemService orderItemService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemDTO>> getItemsByOrder(@PathVariable UUID orderId) {
        List<OrderItemDTO> items = orderItemService.getItemsByOrderId(orderId)
                .stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateItem(@PathVariable UUID id,
                                                   @RequestParam int quantity,
                                                   @RequestParam BigDecimal price) {
        OrderItem updated = orderItemService.updateOrderItem(id, quantity, price);
        return ResponseEntity.ok(new OrderItemDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        orderItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}

