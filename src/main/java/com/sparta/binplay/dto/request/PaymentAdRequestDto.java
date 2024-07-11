package com.sparta.binplay.dto.request;

import lombok.Getter;

@Getter
public class PaymentAdRequestDto {
    private Double totalAmount;

    public PaymentAdRequestDto(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
