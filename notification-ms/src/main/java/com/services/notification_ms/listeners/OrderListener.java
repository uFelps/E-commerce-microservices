package com.services.notification_ms.listeners;

import com.services.notification_ms.dtos.OrderMessageDTO;
import com.services.notification_ms.dtos.PaymentMessageDTO;
import com.services.notification_ms.services.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Autowired
    private NotificationService service;

    @RabbitListener(queues = "notification.order")
    public void receiveNewOrder(@Payload OrderMessageDTO notification) {

        String message = """
                Order Id: %s
                Total: %s
                Customer Id: %s
                Email: %s
                message: %s
                """.formatted(notification.getOrderId(),
                notification.getTotal(),
                notification.getCustomerId(),
                notification.getTo(),
                notification.getMessage());
        System.out.println("Receive the message: \n" + message);
        service.sendNotification(notification);

    }

    @RabbitListener(queues = "notification.payment")
    public void receivePayment(@Payload PaymentMessageDTO notification) {

        String message = """
                Payment Id: %s
                Order Id: %s
                message: %s
                """.formatted(notification.getPaymentId(),
                notification.getOrderId(),
                notification.getMessage());
        System.out.println("Receive the message: \n" + message);
        service.sendNotification(notification);

    }
}
