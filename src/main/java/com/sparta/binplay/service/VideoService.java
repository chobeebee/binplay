package com.sparta.binplay.service;

import com.sparta.binplay.dto.request.VideoRequestDto;
import com.sparta.binplay.dto.response.VideoResponseDto;
import com.sparta.binplay.entity.CustomOAuth2User;
import com.sparta.binplay.entity.Users;
import com.sparta.binplay.entity.Videos;
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

    //비디오 생성
    public VideoResponseDto createVideo(VideoRequestDto videoRequestDto) throws Exception {
        Users user = getAuthenticatedUser();
        Videos video = Videos.of(user, videoRequestDto);
        videoRepository.save(video);
        return VideoResponseDto.from(video);
    }
    
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
        Videos video = videoRepository.findByVideoId(videoId);

        video.update(videoRequestDto);
        videoRepository.save(video);

        return VideoResponseDto.from(video);
    }
    
    // 비디오 삭제
    @Transactional
    public void deleteVideo(Long videoId) {
        Videos video = getVideos(videoId);
        Users user = video.getUser();
        user.getVideos().remove(video); // User의 비디오 리스트에서 비디오 제거
        videoRepository.delete(video);
    }

    // 비디오 재생 조회수
    @Transactional
    public VideoResponseDto play(Long videoId, String username) {

        Videos videos = getVideos(videoId);
        Users uploadUser = videos.getUser();
        Users watchUser = getByUsername(username);
        boolean matchUser = uploadUser.getUserId().equals(watchUser.getUserId()); //어뷰징에 사용

        if (matchUser) {
            videos.viewUp();
        }

        return VideoResponseDto.from(videos);
    }

    
    //회원 찾기 //ifpresent?
    private Users getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
    }
    
    //비디오 찾기
    private Videos getVideos(Long videoId) {
        return videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("비디오를 찾을 수 없음"));
    }
}
