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
    private int viewingTime;
    private int pausedAt;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public static StreamResponseDto from(Streams stream) {
        return StreamResponseDto.builder()
                .streamId(stream.getStreamId())
                .viewingTime(stream.getViewingTime())
                .pausedAt(stream.getPausedAt())
                .createAt(stream.getCreatedAt())
                .build();
    }
}
