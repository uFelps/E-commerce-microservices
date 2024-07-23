package com.services.notification_ms.dtos;

import java.math.BigDecimal;

public class OrderMessageDTO extends MessageDTO {
    private Long orderId;
    private BigDecimal total;
    private Long customerId;

    public OrderMessageDTO() {
    }

    public OrderMessageDTO(Long orderId, BigDecimal total, Long customerId) {
        this.orderId = orderId;
        this.total = total;
        this.customerId = customerId;
    }

    public OrderMessageDTO(String to, String subject, String message, Long orderId, BigDecimal total, Long customerId) {
        super(to, subject, message);
        this.orderId = orderId;
        this.total = total;
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
