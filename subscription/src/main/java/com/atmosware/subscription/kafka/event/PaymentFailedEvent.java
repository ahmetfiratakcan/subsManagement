package com.atmosware.subscription.kafka.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentFailedEvent {
    private Long subscriptionId;
    private String reason;

}
