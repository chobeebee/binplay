package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.statistic.StatisticVideo;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticVideoResponseDto {
    private long dailyViewCount;
    private int dailyPlayTime;
    private LocalDate createdAt;

    public static StatisticVideoResponseDto from(StatisticVideo statisticVideo) {
        return StatisticVideoResponseDto.builder()
                .dailyViewCount(statisticVideo.getDailyViewCount())
                .dailyPlayTime(statisticVideo.getDailyPlayTime())
                .createdAt(statisticVideo.getCreatedAt())
                .build();
    }
}
