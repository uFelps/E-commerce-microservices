package com.services.order_ms.dtos;

import java.math.BigDecimal;

public record OrderNotificationDTO(Long orderId, BigDecimal total, Long customerId, String email, String message) {
}
