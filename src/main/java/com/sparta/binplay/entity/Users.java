package com.sparta.binplay.entity;

import com.sparta.binplay.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class Users extends Timestamped{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="grade")
    private String grade;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @Column(name="is_active", nullable = false)
    private boolean isActive;

    public Users(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.grade = requestDto.getGrade();
        this.role = requestDto.getRole();
        this.isActive = requestDto.isActive();
    }

    public void update(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.grade = requestDto.getGrade();
        this.role = requestDto.getRole();
        this.isActive = requestDto.isActive();
    }
}
