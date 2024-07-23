package com.services.payment_ms.dto;


public record PaymentMessageDTO(Long paymentId,Long orderId, String message) {
}
