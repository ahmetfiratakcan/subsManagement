package com.atmosware.payment.service.impl;

import com.atmosware.payment.dto.ProcessPayment;
import com.atmosware.payment.entity.CardEntity;
import com.atmosware.payment.entity.SubscriptionEntity;
import com.atmosware.payment.enums.SubscriptionStatus;
import com.atmosware.payment.repository.SubscriptionRepository;
import com.atmosware.payment.service.PaymentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodicallySubscriptionPaymentService {

    private PaymentService paymentService;
    private final SubscriptionRepository subscriptionRepository;

    public PeriodicallySubscriptionPaymentService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Scheduled(cron = "0 */3 * * *")
    private void doPaymentPeriodically() {
        List<SubscriptionEntity> subscriptionEntities = subscriptionRepository.findByEndDateBeforeAndStatus(LocalDate.now(), SubscriptionStatus.ACTIVE);
        for (SubscriptionEntity subscriptionEntity : subscriptionEntities) {
            Optional<CardEntity> optionalCard = subscriptionEntity.getUser().getCards().stream().filter(CardEntity::isActive).findAny();
            if (optionalCard.isEmpty()) {
                throw new RuntimeException("Aktif kart bulunamadı");
            }
            CardEntity card = optionalCard.get();
            ProcessPayment processPayment = ProcessPayment.builder()
                    .subscriptionId(subscriptionEntity.getId())
                    .userId(subscriptionEntity.getUser().getId())
                    .useDefaultCard(true)
                    .cardHolderName(card.getCardHolderName())
                    .expiryDate(card.getExpiryDate())
                    .cardNumber(card.getEncryptedCardNumber())
                    .cvv(card.getEncryptedCvv())
                    .build();
            paymentService.processSubscriptionPayment(processPayment);
        }
    }
}
