package com.atmosware.subscription.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isSuccess;
    private String transactionId;
    private LocalDateTime paymentDate;
    @OneToOne
    @JoinColumn(name = "subscription_id")
    private SubscriptionEntity subscription;

}
