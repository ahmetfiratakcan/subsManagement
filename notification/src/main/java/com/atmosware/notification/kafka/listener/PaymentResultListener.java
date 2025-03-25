package com.atmosware.notification.kafka.listener;

import com.atmosware.notification.kafka.event.PaymentFailedEvent;
import com.atmosware.notification.kafka.event.PaymentSuccessEvent;
import com.atmosware.notification.service.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentResultListener {

    private final MailService mailService;

    public PaymentResultListener(MailService mailService) {
        this.mailService = mailService;
    }


    @KafkaListener(topics = "payment-success", groupId = "subscription-service", containerFactory = "kafkaListenerContainerFactory")
    public void handleSuccess(PaymentSuccessEvent event) {
        mailService.sendPaymentSuccedeedMail(event);
    }

    @KafkaListener(topics = "payment-failed", groupId = "subscription-service", containerFactory = "kafkaListenerContainerFactory")
    public void handleFail(PaymentFailedEvent event) {
        mailService.sendPaymentFailedMail(event);
    }
}
