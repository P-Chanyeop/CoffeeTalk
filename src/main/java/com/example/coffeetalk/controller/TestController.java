package com.example.coffeetalk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {
    // TODO : 리액트 연동 테스트
    @GetMapping("/test")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("Test API is working!");
    }


    @GetMapping("/showMe")
    public List<String> hello() {
        return Arrays.asList("첫번째", "두번째");
    }
}
