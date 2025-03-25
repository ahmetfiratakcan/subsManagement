package com.atmosware.payment.repository;

import com.atmosware.payment.entity.SubscriptionEntity;
import com.atmosware.payment.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity,Long> {
    List<SubscriptionEntity> findByEndDateBeforeAndStatus(LocalDate endDate, SubscriptionStatus status);


}
