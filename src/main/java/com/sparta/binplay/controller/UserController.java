package com.sparta.binplay.controller;

import com.sparta.binplay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    @ResponseBody
    public String loginAPI() {
        return "login route";
    }
    
//  //SecurityConfig 파일에 로그아웃 설정 됨
//    @PostMapping("/logout")
//    public String logoutAPI(@AuthenticationPrincipal CustomOAuth2User customUserDetails) {
//        userService.logout(customUserDetails.getUsername());
//        return "로그아웃 완료";
//    }
}
