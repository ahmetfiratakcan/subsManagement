package com.atmosware.subscription.kafka.publisher;

import com.atmosware.subscription.kafka.event.SubscriptionCancelledEvent;
import com.atmosware.subscription.kafka.event.SubscriptionCreatedEvent;
import com.atmosware.subscription.kafka.event.SubscriptionUpdateEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public SubscriptionEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSubscriptionCreatedEvent(SubscriptionCreatedEvent event) {
        kafkaTemplate.send("subscription-created", event);
    }

    public void publishSubscriptionUpdateEvent(SubscriptionUpdateEvent event) {
        kafkaTemplate.send("subscription-update", event);
    }

    public void publishSubscriptionCancelledEvent(SubscriptionCancelledEvent event) {
        kafkaTemplate.send("subscription-cancelled", event);
    }
}
