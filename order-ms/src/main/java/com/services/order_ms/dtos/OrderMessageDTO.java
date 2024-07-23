package com.services.order_ms.dtos;

import java.math.BigDecimal;

public record OrderMessageDTO(Long orderId, BigDecimal total, Long customerId, String to, String message) {
}
