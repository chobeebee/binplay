package com.sparta.binplay.service;

import com.sparta.binplay.dto.response.AdViewResponseDto;
import com.sparta.binplay.entity.AdViews;
import com.sparta.binplay.entity.VideoAd;
import com.sparta.binplay.repository.AdViewRepository;
import com.sparta.binplay.repository.VideoAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdViewService {
    private final AdViewRepository adViewRepository;
    private final VideoAdRepository videoAdRepository;

    public AdViewResponseDto saveAdView(Long videoAdId) {
        // 비디오 광고를 videoAdId로 조회하고, 존재하지 않으면 예외를 던집니다.
        VideoAd videoAd = videoAdRepository.findById(videoAdId)
                .orElseThrow(() -> new RuntimeException("비디오 광고를 찾을 수 없음"));

        // 새로운 AdViews 객체를 생성합니다.
        AdViews adView = AdViews.builder()
                .videoAd(videoAd)
                .build();

        // 생성된 AdViews 객체를 저장합니다.
        AdViews savedAdView = adViewRepository.save(adView);

        // 저장된 AdViews 객체를 AdViewResponseDto로 변환하여 반환합니다.
        return AdViewResponseDto.from(savedAdView);
    }

}
