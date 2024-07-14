package com.sparta.binplay.service;

import com.sparta.binplay.repository.AdViewRepository;
import com.sparta.binplay.repository.StatisticVideoRepository;
import com.sparta.binplay.repository.StreamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    //private final StatisticAdRepository statisticAdRepository;
    private final StatisticVideoRepository statisticVideoRepository;
    private final AdViewRepository adViewRepository;
    private final StreamRepository streamRepository;

    //1일 비디오 통계
    /*public void updateDailyViewVideo() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        List<Object[]> results = streamRepository.countViewsAndPlayTime(startOfDay, endOfDay);

        for (Object[] result : results) {
            Videos video = (Videos) result[0];
            Long dailyViewCount = (Long) result[1];
            Long dailyPlayTimeLong = (Long) result[2];
            int dailyPlayTime = dailyPlayTimeLong.intValue();

            StatisticVideo statisticVideo = statisticVideoRepository.findByVideoAndCreatedAt(video, today)
                    .orElseGet(() -> StatisticVideo.builder()
                            .createdAt(today)
                            .video(video)
                            .dailyViewCount(0)
                            .dailyPlayTime(0)
                            .build());


            statisticVideo.updateDailyViewAndPlayTime(dailyViewCount,dailyPlayTime);
            statisticVideoRepository.save(statisticVideo);
        }
    }*/

    //1일 광고 통계
    /*public void updateDailyViewAd() {
        LocalDate today = LocalDate.now();
        List<Object[]> results = adViewRepository.countViews(today);

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
    }*/

    public List<Object[]> getTop5VideosByViewsForDay(LocalDate date) {
        return statisticVideoRepository.findTop5VideosByViewsForDay(date);
    }

    public List<Object[]> getTop5VideosByPlayTimeForDay(LocalDate date) {
        return statisticVideoRepository.findTop5VideosByPlayTimeForDay(date);
    }

    public List<Object[]> getTop5VideosByViewsForWeek(LocalDate date) {
        LocalDate startOfWeek = date.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = date.with(java.time.DayOfWeek.SUNDAY);
        return statisticVideoRepository.findTop5VideosByViewsForPeriod(startOfWeek, endOfWeek);
    }

    public List<Object[]> getTop5VideosByPlayTimeForWeek(LocalDate date) {
        LocalDate startOfWeek = date.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = date.with(java.time.DayOfWeek.SUNDAY);
        return statisticVideoRepository.findTop5VideosByPlayTimeForPeriod(startOfWeek, endOfWeek);
    }

    public List<Object[]> getTop5VideosByViewsForMonth(LocalDate date) {
        LocalDate startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return statisticVideoRepository.findTop5VideosByViewsForPeriod(startOfMonth, endOfMonth);
    }

    public List<Object[]> getTop5VideosByPlayTimeForMonth(LocalDate date) {
        LocalDate startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return statisticVideoRepository.findTop5VideosByPlayTimeForPeriod(startOfMonth, endOfMonth);
    }
}
