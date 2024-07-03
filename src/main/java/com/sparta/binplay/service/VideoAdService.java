package com.sparta.binplay.service;

import com.sparta.binplay.dto.response.VideoAdResponseDto;
import com.sparta.binplay.entity.VideoAd;
import com.sparta.binplay.repository.AdRepository;
import com.sparta.binplay.repository.VideoAdRepository;
import com.sparta.binplay.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoAdService {
    private final VideoAdRepository videoAdRepository;
    private final VideoRepository videoRepository;
    private final AdRepository adRepository;

    // 특정 광고 재생 횟수 업데이트
    public VideoAdResponseDto updateAdCount(Long videoAdId) {
        VideoAd videoAd = videoAdRepository.findById(videoAdId).orElseThrow(() -> new RuntimeException("영상을 찾을 수 없습니다."));

        videoAd.countAd();
        videoAdRepository.save(videoAd);

        return VideoAdResponseDto.from(videoAd);
    }
}
