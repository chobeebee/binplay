package com.sparta.binplay.service;

import com.sparta.binplay.dto.response.StatisticResponseDto;
import com.sparta.binplay.entity.Statistics;
import com.sparta.binplay.repository.StatisticRepository;
import com.sparta.binplay.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;
    private final VideoRepository videoRepository;
    
    //모든 통계 조회
    public List<StatisticResponseDto> getAllStatistics() throws Exception {
        List<Statistics> statistics = statisticRepository.findAll();
        return statistics.stream()
                .map(StatisticResponseDto::from)
                .collect(Collectors.toList());
    }

    //특정 비디오 통계 조회
    public List<StatisticResponseDto> getStatisticList(Long videoId) throws Exception {
        List<Statistics> statistics = statisticRepository.findByVideoVideoId(videoId);
        return statistics.stream()
                .map(StatisticResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<StatisticResponseDto> getTop5VideosByViews(String period) {
        Pageable topFive = PageRequest.of(0, 5);
        return statisticRepository.findTop5ByPeriod(period, topFive);
    }

    public List<StatisticResponseDto> getTop5VideosByViewsLastDay() {
        return getTop5VideosByViews("DAILY");
    }

    public List<StatisticResponseDto> getTop5VideosByViewsLastWeek() {
        return getTop5VideosByViews("WEEKLY");
    }

    public List<StatisticResponseDto> getTop5VideosByViewsLastMonth() {
        return getTop5VideosByViews("MONTHLY");
    }
}
