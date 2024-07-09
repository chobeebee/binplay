package com.sparta.binplay.controller;

import com.sparta.binplay.service.StatisticAdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stat")
public class StatisticController {

    /*private final AdViewService adViewService;

    @PostMapping("/update/dailyViewCounts")
    public void updateDailyViewCounts() {
        adViewService.updateDailyViewCounts();
    }*/

    private final StatisticAdService statisticAdService;

    @PostMapping("/update/dailyViewCounts")
    public void updateDailyViewCounts() {
        statisticAdService.updateDailyViewCounts();
    }
}
