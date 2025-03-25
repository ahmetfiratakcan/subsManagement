package com.atmosware.payment.kafka.event;

import com.atmosware.payment.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionUpdateEvent {
    private Long subscriptionId;
    private Long userId;
    private SubscriptionStatus status;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
