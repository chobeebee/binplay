package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="payments_video")
@NoArgsConstructor
public class PaymentVideo extends Timestamped{
    @Id
    @Column(name="payment_video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentVideoId;

    @Column(name="total_amount", nullable = false)
    private Double totalAmount;

    @CreatedDate
    @Column(name="created_at", updatable = false) //업데이트를 막음
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Videos video;
}
