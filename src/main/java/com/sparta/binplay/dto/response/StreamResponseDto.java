package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.Streams;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreamResponseDto  {
    private Long streamId;
    private int playTime;
    private int pausedAt;
    private LocalDateTime createAt;

    public static StreamResponseDto from(Streams stream) {
        return StreamResponseDto.builder()
                .streamId(stream.getStreamId())
                .playTime(stream.getPlayTime())
                .pausedAt(stream.getPausedAt())
                .createAt(stream.getCreatedAt())
                .build();
    }
}
