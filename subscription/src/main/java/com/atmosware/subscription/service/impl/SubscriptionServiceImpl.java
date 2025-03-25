package com.atmosware.subscription.service.impl;

import com.atmosware.subscription.dto.CancelSubscriptionRequest;
import com.atmosware.subscription.dto.CreateSubscriptionRequest;
import com.atmosware.subscription.dto.UpdateSubscriptionRequest;
import com.atmosware.subscription.entity.SubscriptionEntity;
import com.atmosware.subscription.entity.UserEntity;
import com.atmosware.subscription.enums.SubscriptionStatus;
import com.atmosware.subscription.kafka.event.SubscriptionCancelledEvent;
import com.atmosware.subscription.kafka.event.SubscriptionUpdateEvent;
import com.atmosware.subscription.kafka.publisher.SubscriptionEventPublisher;
import com.atmosware.subscription.kafka.event.SubscriptionCreatedEvent;
import com.atmosware.subscription.repository.SubscriptionRepository;
import com.atmosware.subscription.repository.UserRepository;
import com.atmosware.subscription.service.SubscriptionService;
import com.atmosware.subscription.util.KafkaUtil;
import com.atmosware.subscription.util.SubscriptionUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionEventPublisher kafkaPublisher;


    @Override
    public void createSubscription(CreateSubscriptionRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setUser(user);
        subscription.setType(request.getType());
        subscription.setStatus(SubscriptionStatus.PENDING_PAYMENT);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusDays(SubscriptionUtil.SUBSCRIPTION_PERIOD_DAYS));

        subscription = subscriptionRepository.save(subscription);

        SubscriptionCreatedEvent event = KafkaUtil.getSubscriptionCreatedEvent(request, user, subscription);
        kafkaPublisher.publishSubscriptionCreatedEvent(event);
    }

    @Override
    public void cancelSubscription(CancelSubscriptionRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        SubscriptionEntity subscription = user.getSubscription();
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        subscription.setEndDate(LocalDate.now());

        subscriptionRepository.save(subscription);

        SubscriptionCancelledEvent subscriptionCancelledEvent = KafkaUtil.getSubscriptionCancelledEvent(user.getId(), subscription.getId());
        kafkaPublisher.publishSubscriptionCancelledEvent(subscriptionCancelledEvent);
    }

    @Override
    public void updateSubscription(UpdateSubscriptionRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        SubscriptionEntity subscription = user.getSubscription();
        subscription.setType(request.getType());
        subscriptionRepository.save(subscription);

        SubscriptionUpdateEvent event = KafkaUtil.getSubscriptionUpdateEvent(request, user.getId(), subscription.getId());
        kafkaPublisher.publishSubscriptionUpdateEvent(event);
    }

}
