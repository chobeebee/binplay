package com.sparta.binplay.dto.request;

import com.sparta.binplay.entity.VideoAd;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AdViewRequestDto {
    private LocalDate createdAt;
    private VideoAd videoAd;

    @Builder
    public AdViewRequestDto(LocalDate createdAt, VideoAd videoAd) {
        this.createdAt = createdAt;
        this.videoAd = videoAd;
    }
}
