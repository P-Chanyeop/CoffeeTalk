package com.example.coffeetalk.controller;

import com.example.coffeetalk.entity.User;
import com.example.coffeetalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO : 로그인 정보 매핑
    @PostMapping("/login/sign_in")
    public ResponseEntity<String> loginPage(@RequestParam("username") String username, @RequestParam("password") String password) {

        // 로그인 수행
        User user = userService.loginHomePage(username, password);

        return ResponseEntity.badRequest().body("login failed.");
    }
}
