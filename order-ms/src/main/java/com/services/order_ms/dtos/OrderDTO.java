package com.services.order_ms.dtos;

import com.services.order_ms.entities.enuns.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(Long id, LocalDateTime createdOn, BigDecimal total, OrderStatus status, Long customerId, List<OrderItemDTO> items) {
}
