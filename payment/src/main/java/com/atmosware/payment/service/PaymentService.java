package com.atmosware.payment.service;

import com.atmosware.payment.dto.ProcessPayment;
import com.atmosware.payment.kafka.event.SubscriptionCancelledEvent;
import com.atmosware.payment.kafka.event.SubscriptionCreatedEvent;
import com.atmosware.payment.kafka.event.SubscriptionUpdateEvent;

public interface PaymentService {

    void createSubscription(SubscriptionCreatedEvent event);

    void cancelSubscription(SubscriptionCancelledEvent event);

    void updateSubscription(SubscriptionUpdateEvent event);

    void processSubscriptionPayment(ProcessPayment processPayment);
}
