package com.services.notification_ms.services;

import com.services.notification_ms.dtos.OrderMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationSenderService notificationSenderService;


    public void sendNotification(OrderMessageDTO orderMessageDTO){
        notificationSenderService.send(
                orderMessageDTO.email(),
                "Order Created",
                orderMessageDTO.message());
    }
}
