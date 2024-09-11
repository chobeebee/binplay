package com.sparta.binplay.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlyTotalAmountDto {
    private BigDecimal totalVideoAmountForMonth;
    private BigDecimal totalAdAmountForMonth;
    private BigDecimal totalAmountForMonth;
    private List<VideoAmountDto> videoSummaries;

    public MonthlyTotalAmountDto(BigDecimal totalVideoAmountForMonth, BigDecimal totalAdAmountForMonth, List<VideoAmountDto> videoSummaries) {
        this.totalVideoAmountForMonth = totalVideoAmountForMonth;
        this.totalAdAmountForMonth = totalAdAmountForMonth;
        this.totalAmountForMonth = totalVideoAmountForMonth.add(totalAdAmountForMonth);
        this.videoSummaries = videoSummaries;
    }
}
