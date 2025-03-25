package com.atmosware.subscription.controller;

import com.atmosware.subscription.dto.CancelSubscriptionRequest;
import com.atmosware.subscription.dto.CreateSubscriptionRequest;
import com.atmosware.subscription.dto.UpdateSubscriptionRequest;
import com.atmosware.subscription.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<String> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        subscriptionService.createSubscription(request);
        return ResponseEntity.ok("Subscription request received and sent for payment.");
    }

    @PostMapping
    public ResponseEntity<String> cancelSubscription(@RequestBody CancelSubscriptionRequest request) {
        subscriptionService.cancelSubscription(request);
        return ResponseEntity.ok("Subscription request received and sent for payment.");
    }

    @PostMapping
    public ResponseEntity<String> updateSubscription(@RequestBody UpdateSubscriptionRequest request) {
        subscriptionService.updateSubscription(request);
        return ResponseEntity.ok("Subscription request received and sent for payment.");
    }

}
