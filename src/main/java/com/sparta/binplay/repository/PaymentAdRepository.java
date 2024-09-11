package com.sparta.binplay.repository;

import com.sparta.binplay.entity.payment.PaymentAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentAdRepository extends JpaRepository<PaymentAd, Long> {
    List<PaymentAd> findByCreatedAt(LocalDate date);
}
