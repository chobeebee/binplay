package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long userId;
    private String email;
    private String name;
    private String grade;
    private String role;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String token;


    public UserResponseDto(Users user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.grade = user.getGrade();
        this.role = user.getRole().toString();
        this.isActive = user.isActive();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    public UserResponseDto(String token) {
        this.token = token;
    }
}
