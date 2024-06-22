package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="payments")
@NoArgsConstructor
public class Payments extends Timestamped{
    @Id
    @Column(name = "payments_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name="total_amount", nullable = false)
    private Double totalAmount;

    @Column(name="video_calculate", nullable = false)
    private Double videoCalculate;

    @Column(name="ad_calculate", nullable = false)
    private Double adCalculate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
