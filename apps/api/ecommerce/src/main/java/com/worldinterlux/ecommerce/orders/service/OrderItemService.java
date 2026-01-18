package com.worldinterlux.ecommerce.orders.service;

import com.worldinterlux.ecommerce.orders.entity.OrderItem;
import com.worldinterlux.ecommerce.orders.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    public List<OrderItem> getItemsByOrderId(UUID orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }


    public OrderItem updateOrderItem(UUID itemId, int quantity, BigDecimal price) {
        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        item.setQuantity(quantity);
        item.setPrice(price);
        return orderItemRepository.save(item);
    }


    public void deleteItem(UUID itemId) {
        orderItemRepository.deleteById(itemId);
    }
}

