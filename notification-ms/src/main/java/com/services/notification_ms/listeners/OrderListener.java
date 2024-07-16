package com.services.notification_ms.listeners;

import com.services.notification_ms.dtos.OrderNotificationDTO;
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
    public void receiveNewOrder(@Payload OrderNotificationDTO notification) {

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
        System.out.println("Receive the message:" + message);
        service.sendNotification(notification);

    }
}
