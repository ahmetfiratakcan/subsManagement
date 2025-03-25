package com.atmosware.notification.kafka.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentSuccessEvent {
    private Long subscriptionId;
    private String paymentReference;
    private LocalDateTime paidAt;
}
