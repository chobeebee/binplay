package com.sparta.binplay.dto;

import com.sparta.binplay.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Role role;
    private String name;
    private String username;
}
