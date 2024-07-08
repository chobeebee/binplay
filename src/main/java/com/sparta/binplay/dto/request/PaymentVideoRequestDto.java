package com.sparta.binplay.dto.request;

import com.sparta.binplay.entity.Videos;
import lombok.Getter;

@Getter
public class PaymentVideoRequestDto {
    private Double totalAmount;
    private Videos video;

    public PaymentVideoRequestDto(Double totalAmount, Videos video) {
        this.totalAmount = totalAmount;
        this.video = video;
    }
}
