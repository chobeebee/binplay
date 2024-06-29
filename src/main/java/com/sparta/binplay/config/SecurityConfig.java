package com.sparta.binplay.config;

import com.sparta.binplay.jwt.JWTFilter;
import com.sparta.binplay.oauth2.CustomSuccessHandler;
import com.sparta.binplay.service.CustomOAuth2UserService;
import com.sparta.binplay.service.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;
    //private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //oauth2 로그인 설정
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                        .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                );

        //JWTFilter
        http
                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class) //form 로그인 끄면 아예 안 쓰는 애
                .addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/oauth2/authorization/google").permitAll() //구글만
                        //.requestMatchers("/oauth2/authorization/*").permitAll() //여러 소셜 로그인 있을 경우
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated());

        //세션 설정 : STATELESS
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        // CORS 설정
//        http
//                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
//
//                    CorsConfiguration configuration = new CorsConfiguration();
//
//                    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//                    configuration.setAllowedMethods(Collections.singletonList("*"));
//                    configuration.setAllowCredentials(true);
//                    configuration.setAllowedHeaders(Collections.singletonList("*"));
//                    configuration.setMaxAge(3600L);
//
//                    configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
//                    configuration.setExposedHeaders(Collections.singletonList("Authorization"));
//
//                    return configuration;
//                }));

         //로그아웃 설정
        http
                .logout((logout) -> logout
                        .logoutUrl("/api/auth/logout")
                        .addLogoutHandler(new LogoutHandler() {
                            @Override
                            public void logout(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) {
                                Cookie[] cookies = request.getCookies();
                                if (cookies != null) {
                                    for (Cookie cookie : cookies) {
                                        if (cookie.getName().equals("Authorization") ||
                                            cookie.getName().equals("JSESSIONID") ||
                                            cookie.getName().equals("__Host-GAPS")) {
                                            cookie.setValue(null);
                                            cookie.setMaxAge(0);
                                            cookie.setPath("/");
                                            response.addCookie(cookie);
                                        }
                                    }
                                }
                                // Invalidate the session
                                request.getSession().invalidate();
                            }
                        })
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException {
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.sendRedirect("/");
                            }
                        })
                );

        return http.build();
    }
}
