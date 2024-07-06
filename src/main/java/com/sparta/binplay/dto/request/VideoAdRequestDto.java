package com.sparta.binplay.dto.request;

import com.sparta.binplay.entity.AdViews;
import com.sparta.binplay.entity.Videos;
import lombok.Getter;

@Getter
public class VideoAdRequestDto {
    private Videos video;
    private AdViews adViews;
}
