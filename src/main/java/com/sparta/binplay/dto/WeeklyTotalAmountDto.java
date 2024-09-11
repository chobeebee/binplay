package com.sparta.binplay.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeeklyTotalAmountDto {
    private BigDecimal totalVideoAmountForWeek;
    private BigDecimal totalAdAmountForWeek;
    private BigDecimal totalAmountForWeek;
    private List<VideoAmountDto> videoSummaries;

    public WeeklyTotalAmountDto(BigDecimal totalVideoAmountForWeek, BigDecimal totalAdAmountForWeek, List<VideoAmountDto> videoSummaries) {
        this.totalVideoAmountForWeek = totalVideoAmountForWeek;
        this.totalAdAmountForWeek = totalAdAmountForWeek;
        this.totalAmountForWeek = totalVideoAmountForWeek.add(totalAdAmountForWeek);
        this.videoSummaries = videoSummaries;
    }
}
