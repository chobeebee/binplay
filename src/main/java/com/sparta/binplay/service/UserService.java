package com.sparta.binplay.service;

import com.sparta.binplay.repository.UserRepository;
import com.sparta.binplay.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

//    public void logout(String username) {
//        Users user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
//    }

}
