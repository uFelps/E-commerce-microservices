package com.services.notification_ms.services;

public interface NotificationSenderService {

    void send(String to, String subject, String body);
}
