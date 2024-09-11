package com.sparta.binplay.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoAmountDto {
    private String videoId;
    private BigDecimal videoAmount;
    private BigDecimal adAmount;

    // 비디오 금액을 더하는 메서드
    public VideoAmountDto addVideoAmount(BigDecimal amount) {
        this.videoAmount = this.videoAmount.add(amount);
        return this; // 메서드 체이닝을 위해 반환
    }

    // 광고 금액을 더하는 메서드
    public VideoAmountDto addAdAmount(BigDecimal amount) {
        this.adAmount = this.adAmount.add(amount);
        return this; // 메서드 체이닝을 위해 반환
    }

    public BigDecimal getTotalAmount() {
        return videoAmount.add(adAmount);
    }
}
