package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="payments_ad")
@NoArgsConstructor
public class PaymentAd {
    @Id
    @Column(name="payment_ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentAdId;

    @Column(name="total_amount", nullable = false)
    private Double totalAmount;

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "ad_view_id", nullable = false)
    private AdViews adView;
}
