package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="statistics_video")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticVideo extends Timestamped{
    @Id
    @Column(name = "stat_video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statVideoId;

    @Column(name="daily_view_count", nullable = false)
    private long dailyViewCount;

    @Column(name="daily_play_time", nullable = false)
    private int dailyPlayTime;

    @CreatedDate
    @Column(name="created_at", updatable = false) //업데이트를 막음
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Videos video;

    public StatisticVideo(long dailyViewCount, int dailyPlayTime, Videos video) {
        this.dailyViewCount = dailyViewCount;
        this.dailyPlayTime = dailyPlayTime;
        this.video = video;
    }

    public StatisticVideo update(Long diailyViewCount, Integer dailyPlayTime) {
        return StatisticVideo.builder()
                .statVideoId(this.statVideoId)  // ID를 유지하여 동일 엔티티로 인식
                .dailyViewCount(diailyViewCount)
                .dailyPlayTime(dailyPlayTime)
                .video(this.video)
                .build();
    }
}
