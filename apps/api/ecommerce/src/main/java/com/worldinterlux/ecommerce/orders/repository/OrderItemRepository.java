package com.worldinterlux.ecommerce.orders.repository;

import com.worldinterlux.ecommerce.orders.entity.Order;
import com.worldinterlux.ecommerce.orders.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    List<OrderItem> findByOrderId(UUID orderId);

}
