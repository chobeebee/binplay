package com.sparta.binplay.service;

import com.sparta.binplay.dto.response.AdViewResponseDto;
import com.sparta.binplay.entity.AdViews;
import com.sparta.binplay.entity.VideoAd;
import com.sparta.binplay.repository.AdViewRepository;
import com.sparta.binplay.repository.VideoAdRepository;
import com.sparta.binplay.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdViewService {
    private final AdViewRepository adViewRepository;
    private final VideoAdRepository videoAdRepository;
    private final VideoRepository videoRepository;

    //광고 시청 기록
    public AdViewResponseDto saveAdView(Long videoAdId) {

        VideoAd videoAd = videoAdRepository.findById(videoAdId)
                .orElseThrow(() -> new RuntimeException("비디오 광고를 찾을 수 없음"));

        AdViews adView = AdViews.builder()
                .videoAd(videoAd)
                .build();

        AdViews savedAdView = adViewRepository.save(adView);

        return AdViewResponseDto.from(savedAdView);
    }
}
