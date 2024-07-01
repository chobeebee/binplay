package com.sparta.binplay.dto.request;

import com.sparta.binplay.entity.Ads;
import com.sparta.binplay.entity.Videos;
import lombok.Getter;

@Getter
public class VideoAdRequestDto {
    private Long adViews;
    private Videos video;
    private Ads ad;
}
