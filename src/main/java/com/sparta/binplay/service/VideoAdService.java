package com.sparta.binplay.service;

import com.sparta.binplay.dto.response.VideoAdResponseDto;
import com.sparta.binplay.entity.Ads;
import com.sparta.binplay.entity.VideoAd;
import com.sparta.binplay.entity.Videos;
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
    public VideoAdResponseDto updateAdCount(Long adId, Long videoId) {
        Ads ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("광고를 찾을 수 없음"));
        Videos video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("비디오를 찾을 수 없음"));
        VideoAd videoAd = videoAdRepository.findByVideoAndAd(video, ad).orElse(VideoAd.builder() //joincolumn 고칠것!!!
                .ad(ad)
                .video(video)
                .build());

        videoAd.countAd();
        videoAdRepository.save(videoAd);

        return VideoAdResponseDto.from(videoAd);
    }
}
