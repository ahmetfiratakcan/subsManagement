package com.atmosware.subscription.kafka.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSuccessEvent {
    private Long subscriptionId;
    private String paymentReference;
    private LocalDateTime paidAt;
}
