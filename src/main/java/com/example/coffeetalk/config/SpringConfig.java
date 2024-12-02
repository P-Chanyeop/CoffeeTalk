package com.example.coffeetalk.config;


import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SpringConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(formLogin ->
                                formLogin
                                        .loginPage("/user/login").permitAll()// 로그인 페이지 경로 지정
                                        .loginProcessingUrl("/user/login/sign_in") // 로그인 폼 전송 URL 지정
                                        .usernameParameter("username") // 사용자명 파라미터 지정
                                        .passwordParameter("password") // 비밀번호 파라미터 지정
                                        .failureUrl("/user/login").permitAll()
                                        .defaultSuccessUrl("/index", true).permitAll()// 로그인 성공 시 기본적으로 이동할 URL
                )
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .invalidSessionUrl("/user/login") // 세션이 유효하지 않을 때 이동할 URL
//                                .maximumSessions(1) // 최대 세션 수
//                                .maxSessionsPreventsLogin(true))
                .headers(headers ->
                        headers
                                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                                .logoutUrl("/user/logout").permitAll()// 로그아웃 URL 지정
                                .logoutSuccessUrl("/user/login") // 로그아웃 성공 시 이동할 URL
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                )
                // CSRF 비활성화
                .csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.build();
//    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
