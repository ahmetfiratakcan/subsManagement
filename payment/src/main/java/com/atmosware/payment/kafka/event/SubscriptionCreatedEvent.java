package com.atmosware.payment.kafka.event;

import com.atmosware.payment.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionCreatedEvent {
    private Long subscriptionId;
    private Long userId;
    private SubscriptionType type;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String paymentRequestId;

}
