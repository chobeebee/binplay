package com.sparta.binplay.entity.statistic;

import com.sparta.binplay.entity.VideoAd;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Getter
@Table(name="statistic_ad", uniqueConstraints = @UniqueConstraint(columnNames = {"video_ad_id", "created_at"}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(StatisticAdId.class)
public class StatisticAd{

    @Column(name="daily_view_count", nullable = false)
    private long dailyViewCount;

    @Id
    @CreatedDate
    @Column(name="created_at", updatable = false) //업데이트를 막음
    private LocalDate createdAt;

    @Id
    @ManyToOne
    @JoinColumn(name = "video_ad_id", nullable = false)
    private VideoAd videoAd;

   /* @Id
    @Column(name = "video_ad_id", nullable = false)
    private Long videoAd;*/

    public void updateDailyViewCount(long dailyViewCount) {
        this.dailyViewCount = dailyViewCount;
    }
}
