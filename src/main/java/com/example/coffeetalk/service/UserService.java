package com.example.coffeetalk.service;

import com.example.coffeetalk.entity.User;
import com.example.coffeetalk.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(String username, String password){
        // password Bcrpyt로 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encryptedPassword);
        newUser.setUserRole("user");

        return this.userRepository.save(newUser);
    }

    public User loginHomePage(String username, String password) {

        // password Bcrpyt로 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(password);

        User findUser = this.userRepository.findByUsername(username);

        if (findUser == null) {
            log.error("사용자를 찾을 수 없습니다.");
            return null;
        }

        if (passwordEncoder.matches(encryptedPassword, findUser.getPassword())) {

            // 로그인 성공
            log.info(username + "계정으로 로그인 성공.");
            return findUser;

        }else {
            log.error("비밀번호가 일치하지 않습니다.");
        }
        return null;
    }

    public User findUser(String username) {
        return this.userRepository.findByUsername(username);
    }
}
