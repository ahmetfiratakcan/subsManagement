package com.atmosware.payment.kafka.event;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSuccessEvent {
    private Long subscriptionId;
    private String paymentReference;
    private LocalDate paidAt;
}
