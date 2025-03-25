package com.atmosware.payment.repository;

import com.atmosware.payment.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity,Long> {
    Optional<CardEntity> findByUser_IdAndUser_Subscription_Id(Long id, Long id1);

}
