package com.services.notification_ms.services;

import com.services.notification_ms.dtos.OrderNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationSenderService notificationSenderService;


    public void sendNotification(OrderNotificationDTO orderNotificationDTO){
        notificationSenderService.send(
                orderNotificationDTO.email(),
                "Order Created",
                orderNotificationDTO.message());
    }
}
