package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.StreamRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "streams")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Streams extends Timestamped {
    @Id
    @Column(name = "stream_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streamId;

    @Column(name = "viewing_time")
    private int viewingTime;

    @Column(name = "paused_at")
    private int pausedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Videos video;

    public Streams(StreamRequestDto streamRequestDto) {
        this.viewingTime = streamRequestDto.getViewingTime();
        this.pausedAt = streamRequestDto.getPausedAt();
        this.user = streamRequestDto.getUser();
        this.video = streamRequestDto.getVideo();
    }

    public void updatePause(int stopTime) {
        this.pausedAt = stopTime;
    }

    public void updateViewingTime(int stopTime) {

        this.viewingTime+= (stopTime - this.viewingTime);

        //영상을 끝까지 봤다면 영상 처음으로 돌림
        if(this.viewingTime == video.getVideoLength()) {
            this.viewingTime = 0;
        }
    }

}
