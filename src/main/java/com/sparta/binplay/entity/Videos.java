package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.VideoRequestDto;
import com.sparta.binplay.entity.payment.PaymentVideo;
import com.sparta.binplay.entity.statistic.StatisticVideo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//@Setter
@Table(name = "videos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Videos extends Timestamped {
    @Id
    @Column(name = "video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "views_count", nullable = false)
    private long viewCount;

    @Column(name = "video_length", nullable = false)
    private int videoLength;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Builder.Default
    @OneToMany(mappedBy = "video")
    private List<Streams> streams = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "video")
    private List<VideoAd> videoAd = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "video")
    private List<StatisticVideo> statisticsVideo = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "video")
    private List<PaymentVideo> paymentsVideo = new ArrayList<>();

    public Videos(String title, String description, int videoLength) {
        this.title = title;
        this.description = description;
        this.videoLength = videoLength;
    }

    public static Videos of(Users user, VideoRequestDto videoRequestDto) {
        return Videos.builder()
                .user(user)
                .title(videoRequestDto.getTitle())
                .description(videoRequestDto.getDescription())
                .videoLength(videoRequestDto.getVideoLength())
                .build();
    }

    public void update(VideoRequestDto videoRequestDto) {
        this.title = videoRequestDto.getTitle();
        this.description = videoRequestDto.getDescription();
        this.videoLength = videoRequestDto.getVideoLength();
    }

    public void viewUp() {
        this.viewCount++;
    }

    public void addVideoAd(VideoAd videoAd) {
        this.videoAd.add(videoAd);
    }
}
