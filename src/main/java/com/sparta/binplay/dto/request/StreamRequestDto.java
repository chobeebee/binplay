package com.sparta.binplay.dto.request;

import lombok.Getter;

@Getter
public class StreamRequestDto {
    private int playTime;
    private int pausedAt;

    public StreamRequestDto(int playTime, int pausedAt) {
        this.playTime = playTime;
        this.pausedAt = pausedAt;
    }

}
