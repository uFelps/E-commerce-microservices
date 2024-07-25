package com.services.order_ms.dtos;

import com.services.order_ms.entities.enuns.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        LocalDateTime createdOn,
        BigDecimal total,
        OrderStatus status,
        @NotNull(message = "Customer Id can'be null")
        Long customerId,
        @Valid
        List<OrderItemDTO> items) {
}
