package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.AdViews;
import com.sparta.binplay.entity.VideoAd;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoAdResponseDto {
    private Long videoAdId;
    private AdViews adViews;

    public static VideoAdResponseDto from(VideoAd videoAd) {
        return VideoAdResponseDto.builder()
                .videoAdId(videoAd.getVideoAdId())
                .adViews(videoAd.getAdView())
                .build();
    }
}
