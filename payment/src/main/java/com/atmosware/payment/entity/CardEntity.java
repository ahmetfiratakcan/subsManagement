package com.atmosware.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardHolderName;
    private String encryptedCardNumber;
    private String encryptedCvv;
    private String expiryDate;
    private boolean isDefault;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
