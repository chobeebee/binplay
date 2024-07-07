package com.sparta.binplay.service;

import com.sparta.binplay.dto.request.VideoAdRequestDto;
import com.sparta.binplay.dto.request.VideoRequestDto;
import com.sparta.binplay.dto.response.VideoResponseDto;
import com.sparta.binplay.entity.*;
import com.sparta.binplay.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final AdViewRepository adViewRepository;
    private final VideoAdRepository videoAdRepository;

    //모든
    public List<Videos> findAllVideos() {
        return videoRepository.findAll();
    }

    //회원 찾기
    private Users getAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String username = customUserDetails.getUsername();
        return getByUsername(username);
    }

    //비디오 생성
    @Transactional
    public VideoResponseDto createVideo(VideoRequestDto videoRequestDto) throws Exception {
        Users user = getAuthenticatedUser();
        // Videos 엔티티를 먼저 저장
        Videos saveVideo = videoRepository.save(Videos.of(user, videoRequestDto));

        // 광고 개수 계산 및 저장
        int numberOfAds = (int) (saveVideo.getVideoLength() / (5 * 60)); // 5분마다 1개의 광고 (5분 = 300초)
        List<Ads> adsList = adRepository.findAll();

        Random random = new Random();

        for (int i = 0; i < numberOfAds; i++) {
            int randomAdIndex = random.nextInt(adsList.size());
            Ads ad = adsList.get(randomAdIndex);

            // VideoAdRequestDto 생성
            VideoAdRequestDto videoAdRequestDto = VideoAdRequestDto.builder()
                    .ad(ad)
                    .viewCount(0)
                    .video(saveVideo)
                    .statIs(false)
                    .build();

            // VideoAd 생성 및 저장
            VideoAd videoAd = VideoAd.of(videoAdRequestDto);
            VideoAd savedVideoAd = videoAdRepository.save(videoAd);
        }

        return VideoResponseDto.from(saveVideo);
    }

    /*@Transactional
    public void matchAd(VideoRequestDto videoRequestDto) throws Exception {
        Users user = getAuthenticatedUser();
        Videos savedVideo = videoRepository.save(Videos.of(user, videoRequestDto));

        // 광고 개수 계산 및 저장
        int numberOfAds = (int) (savedVideo.getVideoLength() / (5 * 60)); // 5분마다 1개의 광고 (5분 = 300초)
        List<Ads> adsList = adRepository.findAll();

        Random random = new Random();

        for (int i = 0; i < numberOfAds; i++) {
            int randomAdIndex = random.nextInt(adsList.size());
            Ads ad = adsList.get(randomAdIndex);

            // VideoAdRequestDto 생성
            VideoAdRequestDto videoAdRequestDto = VideoAdRequestDto.builder()
                    .ad(ad)
                    .viewCount(0)
                    .video(savedVideo)
                    .statIs(false)
                    .build();

            // VideoAd 생성 및 저장
            VideoAd videoAd = VideoAd.of(videoAdRequestDto);
            VideoAd savedVideoAd = videoAdRepository.save(videoAd);

            // AdViewsRequestDto 생성
            AdViewRequestDto adViewsRequestDto = AdViewRequestDto.builder()
                    .createdAt(LocalDate.now()) // 예시로 현재 날짜 사용
                    .videoAd(savedVideoAd)
                    .build();

            // AdViews 생성 및 저장
            AdViews adView = AdViews.of(adViewsRequestDto);
            AdViews saveAdView = adViewRepository.save(adView);
        }
    }*/

    //비디오 조회
    public List<VideoResponseDto> getVideoList() throws Exception {
        Users user = getAuthenticatedUser();
        Long userId = user.getUserId();
        List<Videos> videos = videoRepository.findByUserUserId(userId);
        List<VideoResponseDto> dtos = videos.stream()
                .map(VideoResponseDto::from)
                .collect(Collectors.toList());
        return dtos;
    }

    //비디오 수정
    @Transactional
    public VideoResponseDto updateVideo(Long videoId, VideoRequestDto videoRequestDto) throws Exception {
        Users user = getAuthenticatedUser(); //권한
        Videos video = videoRepository.findByVideoId(videoId).orElseThrow(() -> new RuntimeException("비디오를 찾을 수 없음"));

        video.update(videoRequestDto);
        videoRepository.save(video);

        return VideoResponseDto.from(video);
    }

    // 비디오 삭제
    @Transactional
    public void deleteVideo(Long videoId) throws Exception {
        Videos video = getVideos(videoId);
        Users user = video.getUser();
        user.getVideos().remove(video); // User의 비디오 리스트에서 비디오 제거
        videoRepository.delete(video);

        // Video 엔티티 삭제
        videoRepository.delete(video);
    }

    //회원 찾기
    private Users getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
    }

    //비디오 찾기
    private Videos getVideos(Long videoId) {
        return videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("비디오를 찾을 수 없음"));
    }
}
