package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.VideoAdRequestDto;
import com.sparta.binplay.entity.payment.PaymentAd;
import com.sparta.binplay.entity.statistic.StatisticAd;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Getter
@Table(name="video_ad")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class VideoAd {
    @Id
    @Column(name = "video_ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoAdId;

    @Column(name="view_count", nullable = false)
    private long viewCount;

    @Column(name="stat_is")
    private boolean statIs;

    @OneToMany(mappedBy = "videoAd")
    private List<AdViews> adView;

    @OneToMany(mappedBy = "videoAd")
    private List<StatisticAd> statisticAd;

    @OneToMany(mappedBy = "videoAd")
    private List<PaymentAd> paymentAd;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Videos video;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ads ad;


    public VideoAd(Ads ad, Videos video, int viewCount, boolean statIs) {
        this.ad = ad;
        this.video = video;
        this.viewCount = viewCount;
        this.statIs = statIs;
    }

    public static VideoAd of(VideoAdRequestDto videoAdRequestDto) {
        return VideoAd.builder()
                .ad(videoAdRequestDto.getAd())
                .viewCount(videoAdRequestDto.getViewCount())
                .video(videoAdRequestDto.getVideo())
                .statIs(videoAdRequestDto.isStatIs())
                .build();
    }
}
