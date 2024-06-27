package com.sparta.binplay.controller;

import com.sparta.binplay.dto.response.UserResponseDto;
import com.sparta.binplay.entity.Users;
import com.sparta.binplay.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable @Positive Long id) {
        Users user = userService.findUser(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            UserResponseDto userDto = new UserResponseDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
    }

//    @PostMapping("/social-signup")
//    public UserResponseDto createUser(@RequestBody UserRequestDto requestDto) {
//        return userService.createUser(requestDto);
//    }

//    @PutMapping("/{id}")
//    public Long updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
//        return userService.updateUser(id, requestDto);
//    }

    @PostMapping("/logout")
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        //userService.logoutUser(request, response);
    }
}
