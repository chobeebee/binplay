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
@Table(name="statistics_ad")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticAd extends Timestamped{
    @Id
    @Column(name = "stat_ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statAdId;

    @Column(name="daily_view_count", nullable = false)
    private long dailyViewCount;

    @CreatedDate
    @Column(name="created_at", updatable = false) //업데이트를 막음
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "ad_view_id", nullable = false)
    private AdViews adView;

    public StatisticAd(Long dailyViewCount, AdViews adView) {
        this.dailyViewCount = dailyViewCount;
        this.adView = adView;
    }
}
