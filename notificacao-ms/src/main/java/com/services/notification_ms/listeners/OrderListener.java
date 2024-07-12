package com.services.notification_ms.listeners;
import com.services.notification_ms.dtos.OrderNotificationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @RabbitListener(queues = "notification.order")
    public void receiveNewOrder(@Payload OrderNotificationDTO notification){
        String message = """
                Order Id: %s
                Total: %s
                Customer Id R$: %s
                Email: %s
                message: %s
                """.formatted(notification.orderId(),
                notification.total(),
                notification.customerId(),
                notification.email(),
                notification.message());
        System.out.println("Receive the message:" + message);
    }
}
