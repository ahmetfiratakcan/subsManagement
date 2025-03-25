package com.atmosware.notification.service;

import com.atmosware.notification.kafka.event.*;

public class MailServiceImpl implements MailService {
    @Override
    public void sendPaymentSuccedeedMail(PaymentSuccessEvent event) {

    }

    @Override
    public void sendPaymentFailedMail(PaymentFailedEvent event) {

    }

    @Override
    public void sendSubscriptionCreatedMail(SubscriptionCreatedEvent event) {

    }

    @Override
    public void sendSubscriptionCancelleddMail(SubscriptionCancelledEvent event) {

    }

    @Override
    public void sendSubscriptionUpdatedMail(SubscriptionUpdateEvent event) {

    }
}
