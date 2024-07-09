package com.sparta.binplay.service;

import com.sparta.binplay.entity.VideoAd;
import com.sparta.binplay.entity.statistic.StatisticAd;
import com.sparta.binplay.repository.AdViewRepository;
import com.sparta.binplay.repository.StatisticAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticAdService {
    private final StatisticAdRepository statisticAdRepository;
    private final AdViewRepository adViewRepository;

    /*@Transactional
    public void updateDailyViewCount(AdViews adView) {
        VideoAd videoAd = adView.getVideoAd();
        LocalDate today = LocalDate.now();

        // 해당 비디오 광고와 오늘 날짜에 해당하는 StatisticAd 엔티티를 찾음
        StatisticAd statisticAd = statisticAdRepository.findByVideoAdAndCreatedAt(videoAd, today)
                .orElseGet(() -> StatisticAd.builder()
                        .videoAd(videoAd)
                        .createdAt(today)
                        .dailyViewCount(0L)
                        .build());

        // dailyViewCount 증가
        statisticAd.updateDailyViewCount(statisticAd.getDailyViewCount() + 1);
        statisticAdRepository.save(statisticAd);
    }
*/
    //1일 광고 통계
    public void updateDailyViewCounts() {
        LocalDate today = LocalDate.now();
        List<Object[]> results = adViewRepository.countViewsByVideoAdAndDate(today);

        for (Object[] result : results) {
            VideoAd videoAd = (VideoAd) result[0];
            //Long videoAd = (Long) result[0];
            Long dailyViewCount = (Long) result[1];

            StatisticAd statisticAd = statisticAdRepository.findByVideoAdAndCreatedAt(videoAd, today)
                    .orElseGet(() -> StatisticAd.builder()
                            .createdAt(today)
                            .videoAd(videoAd)
                            .dailyViewCount(0)
                            .build());

            statisticAd.updateDailyViewCount(dailyViewCount);
            statisticAdRepository.save(statisticAd);
        }
    }
}
