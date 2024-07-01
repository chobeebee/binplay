package com.sparta.binplay.dto.response;

import com.sparta.binplay.entity.Users;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public static UserResponseDto from(Users user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .grade(user.getGrade())
                .role(String.valueOf(user.getRole()))
                .isActive(user.isActive())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserResponseDto(String token) {
        this.token = token;
    }
}
