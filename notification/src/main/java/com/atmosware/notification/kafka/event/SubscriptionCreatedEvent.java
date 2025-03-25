package com.atmosware.notification.kafka.event;


import com.atmosware.notification.enums.SubscriptionType;
import lombok.Data;

@Data
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
