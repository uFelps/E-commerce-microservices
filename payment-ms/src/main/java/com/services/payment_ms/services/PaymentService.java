package com.services.payment_ms.services;

import com.services.payment_ms.dto.OrderMessageDTO;
import com.services.payment_ms.dto.PaymentDTO;
import com.services.payment_ms.dto.PaymentMessageDTO;
import com.services.payment_ms.entities.Payment;
import com.services.payment_ms.entities.enuns.PaymentMethod;
import com.services.payment_ms.entities.enuns.PaymentStatus;
import com.services.payment_ms.repositories.PaymentRepository;
import com.services.payment_ms.services.exceptions.DataNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public void createNewPayment(OrderMessageDTO notification) {
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.PENDING);
        payment.setTotal(notification.total());
        payment.setOrderId(notification.orderId());

        repository.save(payment);
    }

    @Transactional
    public PaymentDTO pay(Long paymentId, PaymentMethod method) {

        Payment payment = repository.findById(paymentId).orElseThrow(() -> new  DataNotFoundException("Payment not found! ID: " + paymentId));

        payment.setStatus(PaymentStatus.PAID);
        payment.setDateTime(LocalDateTime.now());
        payment.setMethod(method);

        repository.save(payment);

        PaymentMessageDTO paymentMessageDTO = new PaymentMessageDTO(
                payment.getId(), payment.getOrderId(),
                "The payment has been approved, your order is confirmed."
        );

        rabbitTemplate.convertAndSend("ex.orders-paid", "", paymentMessageDTO);

        return new PaymentDTO(payment.getId(), payment.getTotal(), payment.getMethod(), payment.getStatus(), payment.getDateTime(), payment.getOrderId());
    }

    public PaymentDTO findPaymentById(Long id) {
        Payment payment = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Payment not found! ID: "+ id));

        return new PaymentDTO(payment.getId(), payment.getTotal(), payment.getMethod(), payment.getStatus(), payment.getDateTime(), payment.getOrderId());
    }
}
