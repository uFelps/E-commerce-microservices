package com.services.notification_ms.services;

import com.services.notification_ms.dtos.MessageDTO;
import com.services.notification_ms.dtos.OrderMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationSenderService notificationSenderService;


    public void sendNotification(MessageDTO messageDTO){
        notificationSenderService.send(
                messageDTO.getTo(),
                "Order Created",
                messageDTO.getMessage());
    }
}
