package com.sparta.binplay.dto.request;

import com.sparta.binplay.entity.Videos;
import lombok.Getter;

@Getter
public class StatisticRequestDto {
    private String period;
    private long periodViews;
    private int totalViewingTime;
    private Videos video;

    public StatisticRequestDto(String period, long periodViews, int totalViewingTime, Videos video) {
        this.period = period;
        this.periodViews = periodViews;
        this.totalViewingTime = totalViewingTime;
        this.video = video;
    }
}
