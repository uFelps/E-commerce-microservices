package com.services.notification_ms.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AmazonSes implements NotificationSenderService {

    @Value("${email.source}")
    private String emailSource;

    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;

    public AmazonSes(AmazonSimpleEmailService amazonSimpleEmailService){
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void send(String to, String subject, String body) {
        SendEmailRequest emailRequest = new SendEmailRequest()
                .withSource(emailSource)
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );

        try {
            amazonSimpleEmailService.sendEmail(emailRequest);
            System.out.println("Email was sent successfully.");
        } catch (AmazonServiceException e) {
            System.out.println("Error on AWS SES. Check your credentials: " + e.getMessage());
        }
    }
}
