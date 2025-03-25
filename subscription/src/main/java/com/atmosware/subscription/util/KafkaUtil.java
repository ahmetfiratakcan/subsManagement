package com.atmosware.subscription.util;

import com.atmosware.subscription.dto.CreateSubscriptionRequest;
import com.atmosware.subscription.dto.UpdateSubscriptionRequest;
import com.atmosware.subscription.entity.SubscriptionEntity;
import com.atmosware.subscription.entity.UserEntity;
import com.atmosware.subscription.kafka.event.SubscriptionCancelledEvent;
import com.atmosware.subscription.kafka.event.SubscriptionCreatedEvent;
import com.atmosware.subscription.kafka.event.SubscriptionUpdateEvent;

import java.util.UUID;

public class KafkaUtil {

    private KafkaUtil(){

    }

    public static SubscriptionCreatedEvent getSubscriptionCreatedEvent(CreateSubscriptionRequest request, UserEntity user, SubscriptionEntity subscription) {
        SubscriptionCreatedEvent event = new SubscriptionCreatedEvent();
        event.setUserId(user.getId());
        event.setSubscriptionId(subscription.getId());
        event.setType(subscription.getType());
        event.setCardHolderName(EncryptionUtil.encrypt(request.getCardHolderName()));
        event.setCardNumber(EncryptionUtil.encrypt(request.getCardNumber()));
        event.setExpiryDate(EncryptionUtil.encrypt(request.getExpiryDate()));
        event.setCvv(EncryptionUtil.encrypt(request.getCvv()));
        event.setPaymentRequestId(UUID.randomUUID().toString());
        return event;
    }

    public static SubscriptionUpdateEvent getSubscriptionUpdateEvent(UpdateSubscriptionRequest request, Long userId, Long subscriptionId) {
        SubscriptionUpdateEvent event = new SubscriptionUpdateEvent();
        event.setUserId(userId);
        event.setSubscriptionId(subscriptionId);
        event.setStatus(request.getStatus());
        event.setCardHolderName(EncryptionUtil.encrypt(request.getCardHolderName()));
        event.setCardNumber(EncryptionUtil.encrypt(request.getCardNumber()));
        event.setExpiryDate(EncryptionUtil.encrypt(request.getExpiryDate()));
        event.setCvv(EncryptionUtil.encrypt(request.getCvv()));
        return event;
    }

    public static SubscriptionCancelledEvent getSubscriptionCancelledEvent(Long userId, Long subscriptionId) {
        SubscriptionCancelledEvent event = new SubscriptionCancelledEvent();
        event.setUserId(userId);
        event.setSubscriptionId(subscriptionId);
        return event;
    }
}
