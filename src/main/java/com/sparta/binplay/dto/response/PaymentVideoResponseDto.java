package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.payment.PaymentVideo;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentVideoResponseDto {
    private Double totalAmount;
    private LocalDate createAt;
    private Long videoId;

    public static PaymentVideoResponseDto from(PaymentVideo paymentVideo) {
        return PaymentVideoResponseDto.builder()
                .totalAmount(paymentVideo.getTotalAmount())
                .createAt(paymentVideo.getCreatedAt())
                .videoId(paymentVideo.getVideo().getVideoId())
                .build();
    }
}
