package com.atmosware.notification.kafka.listener;

import com.atmosware.notification.kafka.event.SubscriptionCancelledEvent;
import com.atmosware.notification.kafka.event.SubscriptionCreatedEvent;
import com.atmosware.notification.kafka.event.SubscriptionUpdateEvent;
import com.atmosware.notification.service.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionResultListener {
    private final MailService mailService;

    public SubscriptionResultListener(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(topics = "subscription-created", groupId = "payment-service")
    public void handleSubscriptionCreated(SubscriptionCreatedEvent event) {
        mailService.sendSubscriptionCreatedMail(event);
    }

    @KafkaListener(topics = "subscription-cancelled", groupId = "payment-service")
    public void handleSubscriptionCancelled(SubscriptionCancelledEvent event) {
        mailService.sendSubscriptionCancelleddMail(event);
    }

    @KafkaListener(topics = "subscription-update", groupId = "payment-service")
    public void handleSubscriptionUpdated(SubscriptionUpdateEvent event) {
        mailService.sendSubscriptionUpdatedMail(event);
    }


}
