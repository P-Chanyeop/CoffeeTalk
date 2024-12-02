package com.example.coffeetalk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    // TODO : 로그인 정보 매핑
    /*@PostMapping("/login_page")
    public ResponseEntity<String> loginPage() {

        // 로그인 수행

        return ResponseEntity.ok("Success");
    }*/
}
