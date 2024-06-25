package com.sparta.binplay.dto;

import com.sparta.binplay.entity.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long userId;
    private String email;
    private String grade;
    private String role;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDto(Users user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.grade = user.getGrade();
        this.role = user.getRole().name();
        this.isActive = user.isActive();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
