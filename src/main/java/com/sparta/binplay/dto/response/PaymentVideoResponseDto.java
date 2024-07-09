package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.payment.PaymentVideo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentVideoResponseDto {
    private Double totalAmount;
    private LocalDateTime createAt;

    public static PaymentVideoResponseDto from(PaymentVideo payment) {
        return PaymentVideoResponseDto.builder()
                .totalAmount(payment.getTotalAmount())
                //.createAt(payment.getCreatedAt())
                .build();
    }
}
