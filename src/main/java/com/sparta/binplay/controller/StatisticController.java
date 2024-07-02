package com.sparta.binplay.controller;

import com.sparta.binplay.dto.response.StatisticResponseDto;
import com.sparta.binplay.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stat")
public class StatisticController {
    private final StatisticService statisticService;

    //특정 비디오 검색
    @GetMapping("/{videoId}")
    public ResponseEntity<List<StatisticResponseDto>> getMyStatistics(@PathVariable Long videoId) throws Exception {
        List<StatisticResponseDto> statistics = statisticService.getStatisticList(videoId);
        return ResponseEntity.status(HttpStatus.OK).body(statistics);
    }

    // 모든 통계를 조회하는 엔드포인트
    @GetMapping("/all")
    public ResponseEntity<List<StatisticResponseDto>> getAllStatistics() throws Exception {
        List<StatisticResponseDto> statistics = statisticService.getAllStatistics();
        return ResponseEntity.status(HttpStatus.OK).body(statistics);
    }

    @GetMapping("/top5/day")
    public ResponseEntity<List<StatisticResponseDto>> getTop5VideosLastDay() {
        return ResponseEntity.ok(statisticService.getTop5VideosByViewsLastDay());
    }

    @GetMapping("/top5/week")
    public ResponseEntity<List<StatisticResponseDto>> getTop5VideosLastWeek() {
        return ResponseEntity.ok(statisticService.getTop5VideosByViewsLastWeek());
    }

    @GetMapping("/top5/month")
    public ResponseEntity<List<StatisticResponseDto>> getTop5VideosLastMonth() {
        return ResponseEntity.ok(statisticService.getTop5VideosByViewsLastMonth());
    }


}
