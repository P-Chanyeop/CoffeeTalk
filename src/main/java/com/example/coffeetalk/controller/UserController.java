package com.example.coffeetalk.controller;

import com.example.coffeetalk.entity.User;
import com.example.coffeetalk.service.UserService;
import com.example.coffeetalk.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // TODO : 로그인 정보 매핑
    // TODO : JWT 토큰 발급 및 인증 처리 구현 필요 2024-12-02
    @PostMapping("/sign_in")
    public ResponseEntity<List<String>> loginPage(@RequestParam("username") String username, @RequestParam("password") String password) {

        // 로그인 수행
        User user = userService.loginHomePage(username, password);

        if (user != null) {
            // 사용자 권한 정보 설정
            List<String> roles = new ArrayList<String>();
            roles.add(user.getUserRole());

            // JWT 토큰 발급 및 반환
            String token = jwtUtil.generateToken(user.getUsername(), roles);
            List<String> response = new ArrayList<String>();
            response.add("로그인 성공");
            response.add(token);

            return ResponseEntity.ok(response);
        }

        List<String> failResponse = new ArrayList<String>();
        failResponse.add("로그인 실패");
        failResponse.add("null");
        return ResponseEntity.badRequest().body(failResponse);
    }

    // TODO : 회원가입 정보 매핑
    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@RequestParam("username") String username, @RequestParam("password") String password) {

        // 중복회원 검사
        User findUser = userService.findUser(username);

        if (findUser != null) {
            return ResponseEntity.badRequest().body("이미 존재하는 회원입니다.");
        }

        // 회원가입 수행
        User user = userService.join(username, password);

        if (user == null) {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }

        return ResponseEntity.ok("회원가입 성공");
    }

    // TODO : 중복 아이디 검사
    @GetMapping("/id_check")
    public ResponseEntity<String> idCheck(@RequestParam("username") String username) {

        // 중복회원 검사
        User findUser = userService.findUser(username);

        if (findUser != null) {
            return ResponseEntity.badRequest().body("이미 존재하는 회원입니다.");
        }

        return ResponseEntity.ok("사용 가능한 아이디입니다.");
    }

}
