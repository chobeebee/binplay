package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.Videos;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoResponseDto {
    private Long videoId;
    private String title;
    private String description;
    private long viewCount;
    private long videoLength;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static VideoResponseDto from(Videos video) {
        return VideoResponseDto.builder()
                .videoId(video.getVideoId())
                .title(video.getTitle())
                .description(video.getDescription())
                .viewCount(video.getViewCount())
                .videoLength(video.getVideoLength())
                .createdAt(video.getCreatedAt())
                .updatedAt(video.getUpdatedAt())
                .build();
    }

}
