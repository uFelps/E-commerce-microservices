package com.services.order_ms.entities;

import com.services.order_ms.entities.enuns.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdOn;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "id.order")
    private List<OrderItem> items;


    public Order() {
    }

    public Order(Long id, LocalDateTime createdOn, BigDecimal total, OrderStatus status) {
        this.id = id;
        this.createdOn = createdOn;
        this.total = total;
        this.status = status;
    }

    public Order(Long id, LocalDateTime createdOn, BigDecimal total, OrderStatus status, Customer customer) {
        this.status = status;
        this.total = total;
        this.createdOn = createdOn;
        this.id = id;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Customer getUsuario() {
        return customer;
    }

    public void setUsuario(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
