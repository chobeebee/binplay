package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name="video_ads")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class VideoAd {
    @Id
    @Column(name = "video_ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoAdId;

    @Column(name = "ad_views")
    private Long adViews = 0L;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Videos video;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ads ad;

    public void countAd() {
        this.adViews++;
    }
}
