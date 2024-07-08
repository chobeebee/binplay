package com.sparta.binplay.service;

import com.sparta.binplay.repository.StatisticVideoRepository;
import com.sparta.binplay.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsVideoService {
    private final StatisticVideoRepository statisticRepository;
    private final VideoRepository videoRepository;

}
