package com.sparta.binplay.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VideoRequestDto {
    @NotBlank(message = "동영상 제목은 필수입니다.")
    private Long videoId;
    private String title;
    private String description;
    private long videoLength;

    public VideoRequestDto(Long videoId, String title, String description, long videoLength) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.videoLength = videoLength;
    }
}
