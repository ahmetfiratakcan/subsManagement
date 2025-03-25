package com.atmosware.payment.kafka.listener;

import com.atmosware.payment.kafka.event.SubscriptionCancelledEvent;
import com.atmosware.payment.kafka.event.SubscriptionCreatedEvent;
import com.atmosware.payment.kafka.event.SubscriptionUpdateEvent;
import com.atmosware.payment.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionResultListener {
    private final PaymentService paymentService;

    public SubscriptionResultListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "subscription-created", groupId = "payment-service")
    public void handleSubscriptionCreated(SubscriptionCreatedEvent event) {
        paymentService.createSubscription(event);
    }

    @KafkaListener(topics = "subscription-cancelled", groupId = "payment-service")
    public void handleSubscriptionCancelled(SubscriptionCancelledEvent event) {
        paymentService.cancelSubscription(event);
    }

    @KafkaListener(topics = "subscription-update", groupId = "payment-service")
    public void handleSubscriptionUpdated(SubscriptionUpdateEvent event) {
        paymentService.updateSubscription(event);
    }


}
