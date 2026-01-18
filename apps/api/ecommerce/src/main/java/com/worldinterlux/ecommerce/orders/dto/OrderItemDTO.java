package com.worldinterlux.ecommerce.orders.dto;

import com.worldinterlux.ecommerce.orders.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private UUID id;
    private UUID productId;
    private int quantity;
    private BigDecimal price;


    public OrderItemDTO(OrderItem item) {
        this.id = item.getId();
        this.productId = item.getProductId();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
    }
}
