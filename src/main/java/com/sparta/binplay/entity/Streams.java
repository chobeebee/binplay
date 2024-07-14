package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.StreamRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "streams")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Streams {
    @Id
    @Column(name = "stream_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streamId;

    @Column(name = "play_time")
    private int playTime;

    @Column(name = "paused_at")
    private int pausedAt;

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Videos video;

    public Streams(StreamRequestDto streamRequestDto) {
        this.playTime = streamRequestDto.getPlayTime();
        this.pausedAt = streamRequestDto.getPausedAt();
    }

    public static Streams of(Users user, Videos video, StreamRequestDto streamRequestDto) {
        return Streams.builder()
                .user(user)
                .video(video)
                .playTime(streamRequestDto.getPlayTime())
                .pausedAt(streamRequestDto.getPausedAt())
                .build();
    }

    public void updatePause(int stopTime) {
        this.pausedAt = stopTime;
    }

    public void updateViewingTime(int stopTime) {
        if(video.getVideoLength() <= playTime + stopTime) {
            this.playTime = video.getVideoLength();
            return;
        }
        this.playTime += stopTime;

        //영상을 끝까지 봤다면 영상 처음으로 돌림
        /*if(this.playTime == video.getVideoLength()) {
            this.playTime = 0;
        }*/
    }


}
