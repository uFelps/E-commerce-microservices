package com.services.order_ms.repositories;

import com.services.order_ms.entities.OrderItem;
import com.services.order_ms.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
