package com.atmosware.subscription.service;

import com.atmosware.subscription.dto.CancelSubscriptionRequest;
import com.atmosware.subscription.dto.CreateSubscriptionRequest;
import com.atmosware.subscription.dto.UpdateSubscriptionRequest;

public interface SubscriptionService {
    void createSubscription(CreateSubscriptionRequest request);

    void cancelSubscription(CancelSubscriptionRequest request);

    void updateSubscription(UpdateSubscriptionRequest request);
}
