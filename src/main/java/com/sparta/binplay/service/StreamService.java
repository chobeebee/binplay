package com.sparta.binplay.service;

import com.sparta.binplay.dto.request.StreamRequestDto;
import com.sparta.binplay.dto.response.StreamResponseDto;
import com.sparta.binplay.entity.CustomOAuth2User;
import com.sparta.binplay.entity.Streams;
import com.sparta.binplay.entity.Users;
import com.sparta.binplay.entity.Videos;
import com.sparta.binplay.repository.StreamRepository;
import com.sparta.binplay.repository.UserRepository;
import com.sparta.binplay.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository streamRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    private Users getAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String username = customUserDetails.getUsername();
        return getByUsername(username);
    }

    // 비디오 재생 내역
    @Transactional
    public StreamResponseDto play(Long videoId, String username, StreamRequestDto streamRequestDto) {

        Videos videos = getVideos(videoId);
        Users user = getByUsername(username);
        Streams streams = streamRepository.save(Streams.of(user, videos, streamRequestDto));

        return StreamResponseDto.from(streams);
    }

    // 비디오 중단 시점 업데이트
    public StreamResponseDto stopPosition(Long videoId, int stopTime, String username) {
        Videos video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("비디오를 찾을 수 없음"));
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
        Streams streams = streamRepository.findByUserAndVideo(user, video).orElse(Streams.builder()
                .user(user)
                .video(video)
                .build());

        streams.updatePause(stopTime);
        streams.updateViewingTime(stopTime);

        streamRepository.save(streams);

        return StreamResponseDto.from(streams);
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
