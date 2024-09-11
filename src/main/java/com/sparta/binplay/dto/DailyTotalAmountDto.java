package com.sparta.binplay.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyTotalAmountDto {
    private BigDecimal totalVideoAmount;
    private BigDecimal totalAdAmount;
    private BigDecimal totalAmount;
    private List<VideoAmountDto> videoBreakdowns;

    public DailyTotalAmountDto(BigDecimal totalVideoAmount, BigDecimal totalAdAmount,
                                  List<VideoAmountDto> videoBreakdowns) {
        this.totalVideoAmount = totalVideoAmount;
        this.totalAdAmount = totalAdAmount;
        this.totalAmount = totalVideoAmount.add(totalAdAmount);
        this.videoBreakdowns = videoBreakdowns;
    }
}
