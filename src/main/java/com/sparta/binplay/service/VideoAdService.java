package com.sparta.binplay.service;

import com.sparta.binplay.repository.AdViewRepository;
import com.sparta.binplay.repository.VideoAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoAdService {
    private final VideoAdRepository videoAdRepository;
    private final AdViewRepository adViewRepository;
}
