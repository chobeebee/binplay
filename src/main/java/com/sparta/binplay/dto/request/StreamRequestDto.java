package com.sparta.binplay.dto.request;

import lombok.Getter;

@Getter
public class StreamRequestDto {
    private int playTime;
    private int pausedAt;

    public StreamRequestDto(int viewingTime) {
        this.playTime = viewingTime;
        this.pausedAt = pausedAt;
    }

}
