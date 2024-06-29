package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Videos> videos;

    public Users(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.username = requestDto.getName();
        this.grade = requestDto.getGrade();
        this.role = requestDto.getRole();
        this.isActive = requestDto.isActive();
    }

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .password(password)
                .username(username)
                .grade(grade)
                .role(role)
                .isActive(isActive)
                .build();
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
