package com.services.order_ms.listeners;

import com.services.order_ms.dtos.PaymentMessageDTO;
import com.services.order_ms.services.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @Autowired
    private OrderService service;

    @RabbitListener(queues = "orders.paid")
    public void receiveMessage(@Payload PaymentMessageDTO notification){
        String message = """
                Payment Id: %s
                Order Id: %s
                message: %s
                """.formatted(notification.id(),
                notification.orderId(),
                notification.message());
        System.out.println("Receive the message: \n" + message);

        service.confirmOrder(notification.orderId());

    }
}
