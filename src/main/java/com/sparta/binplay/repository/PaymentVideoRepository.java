package com.sparta.binplay.repository;

import com.sparta.binplay.entity.payment.PaymentVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentVideoRepository extends JpaRepository<PaymentVideo, Long> {
    List<PaymentVideo> findByCreatedAt(LocalDate date);
}
