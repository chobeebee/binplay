package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.PaymentVideo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentVideoResponseDto {
    private Long paymentId;
    private Double totalAmount;
    private LocalDateTime createAt;

    public static PaymentVideoResponseDto from(PaymentVideo payment) {
        return PaymentVideoResponseDto.builder()
                .paymentId(payment.getPaymentVideoId())
                .totalAmount(payment.getTotalAmount())
                .createAt(payment.getCreatedAt())
                .build();
    }
}
