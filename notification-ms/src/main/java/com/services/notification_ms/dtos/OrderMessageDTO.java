package com.services.notification_ms.dtos;

import java.math.BigDecimal;

public record OrderMessageDTO(Long orderId, BigDecimal total, Long customerId, String email, String message) {
}
