package com.sparta.binplay.oauth2;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 쿠키 삭제
        Cookie jsessionidCookie = new Cookie("JSESSIONID", null);
        jsessionidCookie.setPath("/");
        jsessionidCookie.setHttpOnly(true);
        jsessionidCookie.setMaxAge(0);
        response.addCookie(jsessionidCookie);

        Cookie authCookie = new Cookie("Authorization", null);
        authCookie.setPath("/");
        authCookie.setHttpOnly(true);
        authCookie.setMaxAge(0);
        response.addCookie(authCookie);

        // 로그 추가
        System.out.println("JSESSIONID and Authorization cookies deleted");

        // 로그아웃 성공 후 리디렉션
        response.sendRedirect("/login");
    }
}
