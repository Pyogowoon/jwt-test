package com.pyo.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {


    // 모든 사람이 접근 가능
    @GetMapping("home")
    public String home() {
        return "<h1>home</h1>";
    }

    @PostMapping("token")
    public String token(){
    return "<h1>token</h1>";

    }

}
