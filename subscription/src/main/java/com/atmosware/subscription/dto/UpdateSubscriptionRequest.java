package com.atmosware.subscription.dto;

import com.atmosware.subscription.enums.SubscriptionStatus;
import com.atmosware.subscription.enums.SubscriptionType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubscriptionRequest {

    private Long userId;
    private SubscriptionType type;
    private SubscriptionStatus status;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;

}
