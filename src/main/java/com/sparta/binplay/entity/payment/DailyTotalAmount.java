package com.sparta.binplay.entity.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyTotalAmount {
    private Double totalVideoAmount;
    private Double totalAdAmount;
    private Double totalAmount;
}
