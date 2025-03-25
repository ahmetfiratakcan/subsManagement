package com.atmosware.notification.kafka.event;

import lombok.Data;

@Data
public class SubscriptionCancelledEvent {

    private Long subscriptionId;
    private Long userId;

}
