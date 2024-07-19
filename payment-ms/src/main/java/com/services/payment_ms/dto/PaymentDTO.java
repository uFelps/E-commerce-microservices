package com.services.payment_ms.dto;

import com.services.payment_ms.entities.enuns.PaymentMethod;
import com.services.payment_ms.entities.enuns.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDTO(Long id, BigDecimal total, PaymentMethod method,PaymentStatus status, LocalDateTime dateTime, Long orderId) {
}
