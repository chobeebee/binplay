package com.sparta.binplay.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyTotalAmountDto {
    private Double totalVideoAmount;
    private Double totalAdAmount;
    private Double totalAmount;
}
