package com.sparta.binplay.controller;

import com.sparta.binplay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    @ResponseBody
    public String loginAPI() {
        return "login route";
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponseDto> getUser(@PathVariable @Positive Long id) {
//        Users user = userService.findUser(id);
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            UserResponseDto userDto = new UserResponseDto(user);
//            return new ResponseEntity<>(userDto, HttpStatus.OK);
//        }
//    }
}
