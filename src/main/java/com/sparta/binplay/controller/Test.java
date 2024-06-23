package com.sparta.binplay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @RequestMapping("/sample")
    public String greeting() {
        return "sample!!";
    }
}
