package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.AdViews;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdViewResponseDto {
    private Long adViewId;
    private LocalDate createdAt;
    private Long videoAdId;

    public static AdViewResponseDto from(AdViews adView) {
        return AdViewResponseDto.builder()
                .adViewId(adView.getAdViewId())
                .createdAt(adView.getCreatedAt())
                .videoAdId(adView.getVideoAd().getVideoAdId()) // VideoAd 객체의 ID만 포함
                .build();
    }
}
