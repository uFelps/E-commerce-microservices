package com.services.payment_ms.dto;

import java.math.BigDecimal;

public record OrderMessageDTO(Long orderId, BigDecimal total, Long customerId, String email, String message) {
}
