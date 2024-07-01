package com.sparta.binplay.service;

import com.sparta.binplay.dto.response.StreamResponseDto;
import com.sparta.binplay.entity.Streams;
import com.sparta.binplay.entity.Users;
import com.sparta.binplay.entity.Videos;
import com.sparta.binplay.repository.StreamRepository;
import com.sparta.binplay.repository.UserRepository;
import com.sparta.binplay.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository streamRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

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
}
