package com.sparta.binplay.dto.request;

import com.sparta.binplay.entity.Ads;
import com.sparta.binplay.entity.Videos;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoAdRequestDto {
    private long viewCount;
    private boolean statIs;
    private Videos video;
    private Ads ad;

    @Builder
    public VideoAdRequestDto(Ads ad, int viewCount, Videos video, boolean statIs) {
        this.ad = ad;
        this.viewCount = viewCount;
        this.video = video;
        this.statIs = statIs;
    }
}
