package com.atmosware.notification.service;

import com.atmosware.notification.kafka.event.*;

public interface MailService {
    void sendPaymentSuccedeedMail(PaymentSuccessEvent event);

    void sendPaymentFailedMail(PaymentFailedEvent event);

    void sendSubscriptionCreatedMail(SubscriptionCreatedEvent event);

    void sendSubscriptionCancelleddMail(SubscriptionCancelledEvent event);

    void sendSubscriptionUpdatedMail(SubscriptionUpdateEvent event);
}
