package com.worldinterlux.ecommerce.orders.repository;

import com.worldinterlux.ecommerce.orders.entity.Order;
import com.worldinterlux.ecommerce.orders.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserId(UUID userId);

    List<Order> findByStatus(OrderStatus status);

}
