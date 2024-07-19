package com.services.payment_ms.listeners;

import com.services.payment_ms.dto.OrderMessageDTO;
import com.services.payment_ms.services.PaymentService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Autowired
    private PaymentService service;

    @RabbitListener(queues = "payment.pending")
    public void receiveNewPayment(@Payload OrderMessageDTO notification){
        String message = """
                Order Id: %s
                Total: %s
                Customer Id: %s
                Email: %s
                message: %s
                """.formatted(notification.orderId(),
                notification.total(),
                notification.customerId(),
                notification.email(),
                notification.message());
        System.out.println("Receive the message: \n" + message);
        service.createNewPayment(notification);
    }
}
