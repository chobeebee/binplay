package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.Streams;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StreamResponseDto  {
    private Long streamId;
    private Long viewingTime;
    private Integer pausedAt;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


    public StreamResponseDto(Streams stream) {
        this.streamId = stream.getStreamId();
        this.viewingTime = stream.getViewingTime();
        this.pausedAt = stream.getPausedAt();
        this.createAt = stream.getCreatedAt();
        this.modifiedAt = stream.getUpdatedAt();
    }
}
