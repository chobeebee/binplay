package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.Statistics;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticResponseDto {
    private Long statId;
    private String period;
    private long periodViews;
    private int totalViewingTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static StatisticResponseDto from(Statistics statistic) {
        return StatisticResponseDto.builder()
                .statId(statistic.getStatId())
                .period(statistic.getPeriod())
                .periodViews(statistic.getPeriodViews())
                .totalViewingTime(statistic.getTotalViewingTime())
                .createTime(statistic.getCreatedAt())
                .updateTime(statistic.getUpdatedAt())
                .build();
    }
}
