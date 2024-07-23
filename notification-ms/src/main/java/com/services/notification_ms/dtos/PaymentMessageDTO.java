package com.services.notification_ms.dtos;

public class PaymentMessageDTO extends MessageDTO{

    private Long paymentId;
    private Long orderId;

    public PaymentMessageDTO() {
    }

    public PaymentMessageDTO(Long paymentId, Long orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
    }

    public PaymentMessageDTO(String to, String subject, String message, Long paymentId, Long orderId) {
        super(to, subject, message);
        this.paymentId = paymentId;
        this.orderId = orderId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
