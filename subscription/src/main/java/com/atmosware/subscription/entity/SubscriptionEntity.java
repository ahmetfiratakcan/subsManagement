package com.atmosware.subscription.entity;

import com.atmosware.subscription.enums.SubscriptionStatus;
import com.atmosware.subscription.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToOne(mappedBy = "subscription", cascade = CascadeType.ALL)
    private PaymentEntity payment;

}
