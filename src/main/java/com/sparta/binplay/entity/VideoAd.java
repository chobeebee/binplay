package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
    private boolean stat_is;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Videos video;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ads ad;

    @ManyToOne
    @JoinColumn(name = "ad_view_id", nullable = false)
    private AdViews adView;

    // 생성자
    public VideoAd(Ads ad, Videos video, int viewCount, boolean stat_is, AdViews adView) {
        this.ad = ad;
        this.video = video;
        this.viewCount = viewCount;
        this.stat_is = stat_is;
        this.adView = adView;
    }

    public void setAdView(AdViews adView) {
        this.adView = adView;
    }
}
