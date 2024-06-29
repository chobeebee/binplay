package com.sparta.binplay.dto.request;

import com.sparta.binplay.entity.Users;
import com.sparta.binplay.entity.Videos;
import lombok.Getter;

@Getter
public class StreamRequestDto {
    private Long viewingTime;
    private Integer pausedAt;
    private Users user;
    private Videos video;

    public StreamRequestDto(Long viewingTime, Integer pausedAt, Users user, Videos video) {
        this.viewingTime = viewingTime;
        this.pausedAt = pausedAt;
        this.user = user;
        this.video = video;
    }

}
