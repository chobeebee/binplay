package com.sparta.binplay.controller;

import com.sparta.binplay.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stat")
public class StatisticController {

    private final StatisticService statisticService;
    /*private final AdViewService adViewService;

    @PostMapping("/update/dailyViewCounts")
    public void updateDailyViewCounts() {
        adViewService.updateDailyViewCounts();
    }*/

    /*@PostMapping("/update/dailyViewAd")
    public void updateDailyViewCounts() {
        statisticService.updateDailyViewAd();
    }

    @PostMapping("/update/dailyViewVideo")
    public void updateDailyViewVideo() {
        statisticService.updateDailyViewVideo();
    }*/

    @GetMapping("/views-top5/day")
    public List<Object[]> getTop5VideosByViewsForDay(@RequestParam("date") LocalDate date) {
        return statisticService.getTop5VideosByViewsForDay(date);
    }

    @GetMapping("/playtime-top5/day")
    public List<Object[]> getTop5VideosByPlayTimeForDay(@RequestParam("date") LocalDate date) {
        return statisticService.getTop5VideosByPlayTimeForDay(date);
    }

    @GetMapping("/views-top5/week")
    public List<Object[]> getTop5VideosByViewsForWeek(@RequestParam("date") LocalDate date) {
        return statisticService.getTop5VideosByViewsForWeek(date);
    }

    @GetMapping("/playtime-top5/week")
    public List<Object[]> getTop5VideosByPlayTimeForWeek(@RequestParam("date") LocalDate date) {
        return statisticService.getTop5VideosByPlayTimeForWeek(date);
    }

    @GetMapping("/views-top5/month")
    public List<Object[]> getTop5VideosByViewsForMonth(@RequestParam("date") LocalDate date) {
        return statisticService.getTop5VideosByViewsForMonth(date);
    }

    @GetMapping("/playtime-top5/month")
    public List<Object[]> getTop5VideosByPlayTimeForMonth(@RequestParam("date") LocalDate date) {
        return statisticService.getTop5VideosByPlayTimeForMonth(date);
    }
}
