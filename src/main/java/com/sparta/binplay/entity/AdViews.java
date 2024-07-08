package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.AdViewRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Table(name="ad_views")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AdViews {

    @Id
    @Column(name="ad_view_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adViewId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "video_ad_id")
    private VideoAd videoAd;

    @OneToMany(mappedBy = "adView")
    private Set<StatisticAd> statisticsAds;

    @OneToMany(mappedBy = "adView")
    private Set<PaymentAd> paymentsAds;

    public AdViews(LocalDate createdAt, VideoAd videoAd) {
        this.createdAt = createdAt;
        this.videoAd = videoAd;
    }

    public static AdViews of(AdViewRequestDto adViewRequestDto) {
        return AdViews.builder()
                .createdAt(adViewRequestDto.getCreatedAt())
                .videoAd(adViewRequestDto.getVideoAd())
                .build();
    }

}
