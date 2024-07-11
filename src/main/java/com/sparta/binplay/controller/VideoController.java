package com.sparta.binplay.controller;

import com.sparta.binplay.dto.request.StreamRequestDto;
import com.sparta.binplay.dto.request.VideoRequestDto;
import com.sparta.binplay.dto.response.AdViewResponseDto;
import com.sparta.binplay.dto.response.StreamResponseDto;
import com.sparta.binplay.dto.response.VideoResponseDto;
import com.sparta.binplay.entity.CustomOAuth2User;
import com.sparta.binplay.service.AdViewService;
import com.sparta.binplay.service.StreamService;
import com.sparta.binplay.service.VideoAdService;
import com.sparta.binplay.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;
    private final VideoAdService videoAdService;
    private final StreamService streamService;
    private final AdViewService adViewService;

    //모든 비디오 조회 (필요없지 않나)
    /*@GetMapping
    public ResponseEntity<List<Videos>> getAllVideos() {
        List<Videos> videos = videoService.findAllVideos();
        return ResponseEntity.ok(videos);
    }*/

    // my비디오 리스트 조회
    @GetMapping("/my")
    public ResponseEntity<List<VideoResponseDto>> getMyVideos() throws Exception {
        List<VideoResponseDto> videos = videoService.getVideoList();
        return ResponseEntity.status(HttpStatus.OK).body(videos);
    }

    //비디오 등록
    @PostMapping("/create")
    public ResponseEntity<VideoResponseDto> createVideo(@RequestBody VideoRequestDto videoRequestDto) throws Exception {
        VideoResponseDto videoResponseDto = videoService.createVideo(videoRequestDto);
        //videoService.matchAd(videoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(videoResponseDto);
    }

    //비디오 수정
    @PutMapping("/update/{video-id}")    // http://localhost:8080/videos/update/1
    public ResponseEntity<VideoResponseDto> updateVideo(@PathVariable("video-id") Long videoId, @RequestBody VideoRequestDto videoRequestDto) throws Exception {
        VideoResponseDto videoResponseDto = videoService.updateVideo(videoId,videoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(videoResponseDto);
    }

    //비디오 삭제
    @DeleteMapping("/delete/{video-id}")
    public String deleteVideo(@PathVariable("video-id") Long videoId) throws Exception{
        videoService.deleteVideo(videoId);
        return  "삭제완료";
    }
    
    //비디오 재생
    @PostMapping("/play/{video-id}") //<?> : hidden
    public ResponseEntity<StreamResponseDto> videoPlay(@PathVariable("video-id") Long videoId, @AuthenticationPrincipal CustomOAuth2User user, @RequestBody StreamRequestDto streamRequestDto) {
        return ResponseEntity.ok().body(streamService.play(videoId, user.getUsername(),streamRequestDto));
    }
    
    //비디오 중단
    @PostMapping("/play/{video-id}/stop") //레스풀
    public ResponseEntity<StreamResponseDto> videoStop(@PathVariable("video-id") Long videoId, @RequestBody int stopTime, @AuthenticationPrincipal CustomOAuth2User user) {
        return ResponseEntity.ok().body(streamService.stopPosition(videoId, stopTime, user.getUsername()));
    }

    //광고 시청
    @PostMapping("/play/ads/{video-ad-id}")
    public ResponseEntity<AdViewResponseDto> viewAd(@PathVariable("video-ad-id") Long videoAdId) {
        return ResponseEntity.ok().body(adViewService.saveAdView(videoAdId));
    }
}
