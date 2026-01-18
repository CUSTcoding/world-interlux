package com.worldinterlux.ecommerce.orders.dto;

import com.worldinterlux.ecommerce.orders.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDTO {
    private UUID id;
    private UUID userId;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private String status;
    private Instant createdAt;
    private List<OrderItemDTO> items;


    public OrderDTO(Order order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.totalAmount = order.getTotalAmount();
        this.paidAmount = order.getPaidAmount();
        this.status = order.getStatus() != null ? order.getStatus().name() : null;
        this.createdAt = order.getCreatedAt();
        if (order.getItems() != null) {
            this.items = order.getItems().stream()
                    .map(OrderItemDTO::new)
                    .collect(Collectors.toList());
        }
    }
}

