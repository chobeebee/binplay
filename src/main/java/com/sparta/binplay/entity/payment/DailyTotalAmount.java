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

    public DailyTotalAmount(double totalVideoAmount, double totalAdAmount, double totalAmount) {
        this.totalVideoAmount = totalVideoAmount;
        this.totalAdAmount = totalAdAmount;
        this.totalAmount = totalAmount;
    }

    public double getTotalVideoAmount() {
        return totalVideoAmount;
    }

    public double getTotalAdAmount() {
        return totalAdAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
