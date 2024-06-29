package com.sparta.binplay.service;

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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final StreamRepository streamRepository;
    private final AdRepository adRepository;
    private final VideoAdRepository videoAdRepository;

    //모든
    public List<Videos> findAllVideos() {
        return videoRepository.findAll();
    }


    private Users getAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String username = customUserDetails.getUsername();
        return getByUsername(username);
    }

    public VideoResponseDto createVideo(VideoRequestDto videoRequestDto) throws Exception {
        Users user = getAuthenticatedUser();
        Videos video = Videos.of(user, videoRequestDto);
        videoRepository.save(video);
        return VideoResponseDto.from(video);
    }

    public List<VideoResponseDto> getVideoList() throws Exception {
        Users user = getAuthenticatedUser();
        Long userId = user.getUserId();
        List<Videos> videos = videoRepository.findByUserUserId(userId);
        List<VideoResponseDto> dtos = videos.stream()
                .map(VideoResponseDto::from)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public VideoResponseDto updateVideo(Long videoId, VideoRequestDto videoRequestDto) throws Exception {
        Users user = getAuthenticatedUser(); //권한
        Videos video = videoRepository.findByVideoId(videoId);
        video.setTitle(videoRequestDto.getTitle());
        video.setDescription(videoRequestDto.getDescription());
        video.setVideoLength(videoRequestDto.getVideoLength());
        videoRepository.save(video);
        return new VideoResponseDto(video);
    }

    @Transactional
    public void deleteVideo(Long videoId) {
        Videos video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("비디오 없음"));
        Users user = video.getUser();
        user.getVideos().remove(video); // User의 비디오 리스트에서 비디오 제거
        videoRepository.delete(video);
    }

    @Transactional
    public VideoResponseDto play(Long videoId, String username) {

        Videos videos = getVideos(videoId);
        Users uploadUser = videos.getUser();
        Users watchUser = getByUsername(username);
        boolean matchUser = uploadUser.getUserId().equals(watchUser.getUserId()); //어뷰징에 사용

        if (!matchUser) {
            videos.viewUp();
        }

        return VideoResponseDto.from(videos);

        //jpa는 db에는 객체라는게 없는 데 java는 객체밖에 없는 것을 해결 하기위해 사용
        //연관관계를 맺을 때 id끼리가 아니라 객체 자체임
        //DB관점에서는 id 참조하는 것이고
        //jpa에서 찾을 때는 해당 entity 자체로 찾아야 함
    }


    // 비디오 중단 시점 업데이트
    public void stopPosition(Long videoId, int stopTime, String username) {
        Videos videos = getVideos(videoId);//ctrl+alt+m
        Users users = getByUsername(username);
        Streams streams = streamRepository.findByUsersAndVideos(users, videos).orElse(Streams.builder() //.orElse : 존재하면 패스, 존재하지않으면 생성
                .user(users)
                .video(videos)
                .build());

        streams.updatePause(stopTime);
        streams.updateViewingTime(stopTime);

        streamRepository.save(streams);
    }

    // 특정 광고 재생 횟수 업데이트
    public void updateAdCount(Long adId, Long videoId) {
        Ads ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException(""));
        Videos video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException(""));
        VideoAd videoAd = videoAdRepository.findByVideosAndAds(videos, ads).orElse(VideoAd.builder() //joincolumn 고칠것!!!
                .ad(ad)
                .video(video)
                .build());

        videoAd.countAd();
        videoAdRepository.save(videoAd);
    }

    private Users getByUsername(String username) { //Optional : null 방지 , repository에서 찾았을 떄 null인지 아닌지 몰라서 서비스단에서 처리하라고
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다.")); //초기화, 예외처리 문구, 존재유무 처리
    }//ifpresent?

    private Videos getVideos(Long videoId) {
        return videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("비디오를 찾을 수 없음"));
    }
}
