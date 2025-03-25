package com.atmosware.payment.service.impl;

import com.atmosware.payment.dto.ProcessPayment;
import com.atmosware.payment.entity.CardEntity;
import com.atmosware.payment.entity.PaymentEntity;
import com.atmosware.payment.entity.SubscriptionEntity;
import com.atmosware.payment.entity.UserEntity;
import com.atmosware.payment.enums.SubscriptionStatus;
import com.atmosware.payment.kafka.event.*;
import com.atmosware.payment.kafka.publisher.PaymentEventPublisher;
import com.atmosware.payment.repository.CardRepository;
import com.atmosware.payment.repository.PaymentRepository;
import com.atmosware.payment.repository.SubscriptionRepository;
import com.atmosware.payment.repository.UserRepository;
import com.atmosware.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final PaymentRepository paymentRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentEventPublisher kafkaPublisher;

    public PaymentServiceImpl(UserRepository userRepository, CardRepository cardRepository, PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository, PaymentEventPublisher kafkaPublisher) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.paymentRepository = paymentRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.kafkaPublisher = kafkaPublisher;
    }


    @Override
    public void createSubscription(SubscriptionCreatedEvent subscriptionCreatedEvent) {
        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setStatus(SubscriptionStatus.PENDING_PAYMENT);
        subscriptionRepository.save(subscription);

        ProcessPayment processPayment = ProcessPayment.builder()
                .subscriptionId(subscription.getId())
                .userId(subscriptionCreatedEvent.getUserId())
                .useDefaultCard(false)
                .cardHolderName(subscriptionCreatedEvent.getCardHolderName())
                .cardNumber(subscriptionCreatedEvent.getCardNumber())
                .cvv(subscriptionCreatedEvent.getCvv())
                .expiryDate(subscriptionCreatedEvent.getExpiryDate())
                .build();

        processSubscriptionPayment(processPayment);
    }

    @Override
    public void cancelSubscription(SubscriptionCancelledEvent event) {
        SubscriptionEntity subscription = subscriptionRepository.findById(event.getSubscriptionId())
                .orElseThrow(() -> new RuntimeException("Subcription not found"));
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        subscriptionRepository.save(subscription);
    }

    @Override
    public void updateSubscription(SubscriptionUpdateEvent event) {
        SubscriptionEntity subscription = subscriptionRepository.findById(event.getSubscriptionId())
                .orElseThrow(() -> new RuntimeException("Subcription not found"));
        subscription.setStatus(event.getStatus());
        subscriptionRepository.save(subscription);
    }

    @Override
    public void processSubscriptionPayment(ProcessPayment processPayment) {
        try {
            UserEntity user = userRepository.findById(processPayment.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            boolean paymentSuccess = true;

            if (paymentSuccess) {
                PaymentEntity payment = new PaymentEntity();
                payment.setUser(user);
                payment.setCard(getCardInfo(processPayment));
                payment.setAmount(BigDecimal.valueOf(199));
                payment.setSuccess(true);
                payment.setTransactionId(UUID.randomUUID().toString());
                payment.setCreatedAt(LocalDate.now());

                paymentRepository.save(payment);

                kafkaPublisher.sendPaymentSuccededEvent(processPayment.getSubscriptionId(), payment.getCreatedAt());
            } else {
                kafkaPublisher.sendPaymentFailedEvent(processPayment.getSubscriptionId(), "Card declined");
            }

        } catch (Exception e) {
            kafkaPublisher.sendPaymentFailedEvent(processPayment.getSubscriptionId(), "Payment exception: " + e.getMessage());
        }
    }

    private CardEntity getCardInfo(ProcessPayment processPayment) {
        CardEntity card = new CardEntity();
        card.setEncryptedCardNumber(processPayment.getCardNumber());
        card.setCardHolderName(processPayment.getCardHolderName());
        card.setExpiryDate(processPayment.getExpiryDate());
        card.setEncryptedCvv(processPayment.getCvv());
        return card;
    }

}
