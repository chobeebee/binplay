package com.sparta.binplay.repository;

import com.sparta.binplay.entity.payment.PaymentVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentVideoRepository extends JpaRepository<PaymentVideo, Long> {
    List<PaymentVideo> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    /*Optional<PaymentVideo> findByVideoAndCreatedAt(Videos video, LocalDate date);
*/
  /*  @Query("SELECT p FROM PaymentVideo p WHERE p.createdAt = :date")
    List<PaymentVideo> findAllByCreatedAt(@Param("date") LocalDate date);*/

    /*@Query("SELECT SUM(p.totalAmount) FROM PaymentVideo p WHERE p.createdAt = :date")
    Double findTotalAmountByCreatedAt(@Param("date") LocalDate date);*/

    @Query("SELECT SUM(p.totalAmount) FROM PaymentVideo p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    Double findTotalAmountByCreatedAtBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
