package com.atmosware.payment.kafka.publisher;

import com.atmosware.payment.kafka.event.PaymentFailedEvent;
import com.atmosware.payment.kafka.event.PaymentSuccessEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentSuccededEvent(Long subscriptionId, LocalDate createdAt) {
        PaymentSuccessEvent successEvent = new PaymentSuccessEvent();
        successEvent.setSubscriptionId(subscriptionId);
        successEvent.setPaidAt(createdAt);
        kafkaTemplate.send("payment-success", successEvent);
    }

    public void sendPaymentFailedEvent(Long subscriptionId, String reason) {
        PaymentFailedEvent failedEvent = new PaymentFailedEvent();
        failedEvent.setSubscriptionId(subscriptionId);
        failedEvent.setReason("Card declined");
        kafkaTemplate.send("payment-failed", failedEvent);
    }
}
