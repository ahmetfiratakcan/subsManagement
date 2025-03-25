package com.atmosware.subscription.kafka.listener;

import com.atmosware.subscription.entity.SubscriptionEntity;
import com.atmosware.subscription.enums.SubscriptionStatus;
import com.atmosware.subscription.kafka.event.PaymentFailedEvent;
import com.atmosware.subscription.kafka.event.PaymentSuccessEvent;
import com.atmosware.subscription.repository.SubscriptionRepository;
import com.atmosware.subscription.util.SubscriptionUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentResultListener {

    private final SubscriptionRepository subscriptionRepository;

    public PaymentResultListener(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @KafkaListener(topics = "payment-success", groupId = "subscription-service", containerFactory = "kafkaListenerContainerFactory")
    public void handleSuccess(PaymentSuccessEvent event) {
        SubscriptionEntity subscription = subscriptionRepository.findById(event.getSubscriptionId()).orElseThrow();
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setEndDate(LocalDate.now().plusDays(SubscriptionUtil.SUBSCRIPTION_PERIOD_DAYS));
        subscriptionRepository.save(subscription);
    }

    @KafkaListener(topics = "payment-failed", groupId = "subscription-service", containerFactory = "kafkaListenerContainerFactory")
    public void handleFail(PaymentFailedEvent event) {
        SubscriptionEntity subscription = subscriptionRepository.findById(event.getSubscriptionId()).orElseThrow();
        subscription.setStatus(SubscriptionStatus.INACTIVE);
        subscriptionRepository.save(subscription);
    }
}
