package com.example.coffeetalk.service;

import com.example.coffeetalk.entity.Member;
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

    public Member join(String username, String password){
        // password Bcrpyt로 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(password);

        Member newMember = new Member();
        newMember.setUsername(username);
        newMember.setPassword(encryptedPassword);
        newMember.setUserRole("member");

        return this.userRepository.save(newMember);
    }

    public Member loginHomePage(String username, String password) {

        // password Bcrpyt로 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encryptedPassword = passwordEncoder.encode(password);

//        log.info("encryptedPassword : " + encryptedPassword);

        Member findMember = this.userRepository.findByUsername(username);
        if (findMember == null) {
            log.error("사용자를 찾을 수 없습니다.");
            return null;
        }

        String encryptedPassword = findMember.getPassword(); // 데이터베이스에서 가져온 암호화된 비밀번호
        if (passwordEncoder.matches(password, encryptedPassword)) {
            log.info("비밀번호가 일치합니다.");
            // 로그인 성공
            log.info(username + "계정으로 로그인 성공.");

            return findMember;
        } else {
            log.info("비밀번호가 일치하지 않습니다.");
        }

        return null;
    }

    public Member findUser(String username) {
        return this.userRepository.findByUsername(username);
    }
}
