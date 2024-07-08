package com.sparta.binplay.repository;

import com.sparta.binplay.entity.PaymentVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAdRepository extends JpaRepository<PaymentVideo, Long> {
}
