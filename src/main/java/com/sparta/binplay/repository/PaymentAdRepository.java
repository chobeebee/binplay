package com.sparta.binplay.repository;

import com.sparta.binplay.entity.payment.PaymentAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PaymentAdRepository extends JpaRepository<PaymentAd, Long> {

    //List<PaymentVideo> findAllByCreatedAt(LocalDate date);

    //Optional<PaymentAd> findByVideoAdAndCreatedAt(VideoAd videoAd, LocalDate date);

    /*@Query("SELECT p FROM PaymentAd p WHERE p.createdAt = :date")
    List<PaymentAd> findAllByCreatedAt(@Param("date") LocalDate date);*/

    /*@Query("SELECT SUM(p.totalAmount) FROM PaymentAd p WHERE p.createdAt = :date")
    Double findTotalAmountByCreatedAt(@Param("date") LocalDate date);*/

    @Query("SELECT SUM(p.totalAmount) FROM PaymentAd p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    Double findTotalAmountByCreatedAtBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
