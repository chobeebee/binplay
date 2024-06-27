package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class Users extends Timestamped{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="username")
    private String username;

    @Column(name="name", nullable = false)
    private String name;

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
        this.username = requestDto.getName();
        this.grade = requestDto.getGrade();
        this.role = requestDto.getRole();
        this.isActive = requestDto.isActive();
    }

    public void update(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.name = requestDto.getName();
        this.grade = requestDto.getGrade();
        this.role = requestDto.getRole();
        this.isActive = requestDto.isActive();
    }
}
