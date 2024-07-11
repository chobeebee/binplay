package com.sparta.binplay.repository;

import com.sparta.binplay.entity.Videos;
import com.sparta.binplay.entity.payment.PaymentVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentVideoRepository extends JpaRepository<PaymentVideo, Long> {
    List<PaymentVideo> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<PaymentVideo> findByVideoAndCreatedAt(Videos video, LocalDate date);

    @Query("SELECT p FROM PaymentVideo p WHERE p.createdAt = :date")
    List<PaymentVideo> findAllByCreatedAt(@Param("date") LocalDate date);
}