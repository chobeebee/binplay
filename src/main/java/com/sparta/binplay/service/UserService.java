package com.sparta.binplay.service;

import com.sparta.binplay.dto.request.UserRequestDto;
import com.sparta.binplay.dto.response.UserResponseDto;
import com.sparta.binplay.entity.Users;
import com.sparta.binplay.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(@Valid UserRequestDto requestDto) {
        Users users = new Users(requestDto);
        Users saveUser = userRepository.save(users);
        return new UserResponseDto(saveUser);
    }

    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        // Spring Security를 사용한 로그아웃 처리
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @Transactional
    public Long updateUser(Long userId, UserRequestDto requestDto) {
        Users user = findUser(userId);
        user.update(requestDto);
        return userId;
    }

    public Users findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 회원은 존재하지 않습니다.")
        );
    }



    @Transactional
    public Long deleteUser(Long userId) {
        Users user = findUser(userId);
        userRepository.delete(user);
        return userId;
    }


    public void loginUser(HttpServletRequest request, HttpServletResponse response) {
    }
}
