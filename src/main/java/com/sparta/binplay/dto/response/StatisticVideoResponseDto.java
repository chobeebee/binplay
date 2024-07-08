package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.StatisticVideo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticVideoResponseDto {
    private Long statVideoId;
    private long dailyViewCount;
    private int dailyPlayTime;
    private LocalDateTime createdAt;

    public static StatisticVideoResponseDto from(StatisticVideo statisticVideo) {
        return StatisticVideoResponseDto.builder()
                .statVideoId(statisticVideo.getStatVideoId())
                .dailyViewCount(statisticVideo.getDailyViewCount())
                .dailyPlayTime(statisticVideo.getDailyPlayTime())
                .createdAt(statisticVideo.getCreatedAt())
                .build();
    }
}
