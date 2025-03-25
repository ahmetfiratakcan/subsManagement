package com.atmosware.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessPayment {

    private Long userId;
    private Long subscriptionId;
    private boolean useDefaultCard;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
