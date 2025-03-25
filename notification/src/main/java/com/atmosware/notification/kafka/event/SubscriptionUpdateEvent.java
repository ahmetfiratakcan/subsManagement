package com.atmosware.notification.kafka.event;

import com.atmosware.notification.enums.SubscriptionStatus;
import lombok.Data;

@Data
public class SubscriptionUpdateEvent {

    private Long subscriptionId;
    private Long userId;
    private SubscriptionStatus status;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
