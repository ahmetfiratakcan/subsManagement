package com.atmosware.notification.kafka.event;

import lombok.Data;

@Data
public class PaymentFailedEvent {

    private Long subscriptionId;
    private String reason;
}
