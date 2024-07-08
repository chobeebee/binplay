package com.sparta.binplay.repository;

import com.sparta.binplay.entity.PaymentVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentVideoRepository extends JpaRepository<PaymentVideo, Long> {
    List<PaymentVideo> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
