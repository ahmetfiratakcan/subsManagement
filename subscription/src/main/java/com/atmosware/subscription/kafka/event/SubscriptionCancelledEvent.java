package com.atmosware.subscription.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionCancelledEvent {
    private Long subscriptionId;
    private Long userId;
}
