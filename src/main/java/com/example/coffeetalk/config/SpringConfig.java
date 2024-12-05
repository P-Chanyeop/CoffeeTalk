package com.example.coffeetalk.config;


import com.example.coffeetalk.utility.JwtAuthenticationFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SpringConfig implements WebMvcConfigurer {

    /*
    * TODO : JWT 인증 필터 추가
    */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SpringConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
                 // X-Frame-Options 설정
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/h2-console/**").permitAll() // h2-console 경로는 인증 없이 접근 가능
                        .requestMatchers("/**").permitAll() // 인증 없이 접근 가능한 경로
                        /*.anyRequest().authenticated() // 그 외 경로는 인증 필요*/
                )
                // cors 비활성화
                /*.cors(AbstractHttpConfigurer::disable)*/
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                ; // JWT 필터 추가
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS를 허용
                .allowedOrigins("http://localhost:3000") // 허용할 출처
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 자격 증명 허용
    }


    /*@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(formLogin ->
                                formLogin
                                        .loginPage("/member/login").permitAll()// 로그인 페이지 경로 지정
                                        .loginProcessingUrl("/member/login/sign_in") // 로그인 폼 전송 URL 지정
                                        .usernameParameter("username") // 사용자명 파라미터 지정
                                        .passwordParameter("password") // 비밀번호 파라미터 지정
                                        .failureUrl("/member/login").permitAll()
                                        .defaultSuccessUrl("/index", true).permitAll()// 로그인 성공 시 기본적으로 이동할 URL
                )
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .invalidSessionUrl("/member/login") // 세션이 유효하지 않을 때 이동할 URL
//                                .maximumSessions(1) // 최대 세션 수
//                                .maxSessionsPreventsLogin(true))
                .headers(headers ->
                        headers
                                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                                .logoutUrl("/member/logout").permitAll()// 로그아웃 URL 지정
                                .logoutSuccessUrl("/member/login") // 로그아웃 성공 시 이동할 URL
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                )

                // CSRF 비활성화
                .csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);

        http.cors().and()
                .authorizeHttpRequests()
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // React 개발 서버
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.build();
//    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }*/
}
