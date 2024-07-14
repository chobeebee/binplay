/*
package com.sparta.binplay.dto.response;

import com.sparta.binplay.batch.domain.payment.PaymentAd;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentAdResponseDto {
    private Double totalAmount;
    private LocalDate createdAt;

    public static PaymentAdResponseDto from(PaymentAd paymentAd) {
        return PaymentAdResponseDto.builder()
                .totalAmount(paymentAd.getTotalAmount())
                .createdAt(paymentAd.getCreatedAt())
                .build();
    }
}
*/
