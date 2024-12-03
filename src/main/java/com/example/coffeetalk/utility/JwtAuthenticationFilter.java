package com.example.coffeetalk.utility;

import com.example.coffeetalk.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 요청에서 JWT 토큰 추출
            String token = jwtUtil.extractToken(request);

            // 토큰이 존재하고 유효한 경우
            if (token != null && jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 이름 추출
                String username = jwtUtil.getUsernameFromToken(token);

                // 권한 정보 추출
                Collection<? extends GrantedAuthority> authorities = jwtUtil.getAuthorities(token);

                // 인증 객체 생성
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // SecurityContext에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            // 인증 처리 중 오류 발생 시 로깅
            System.err.println("JWT 필터 처리 중 오류: " + ex.getMessage());
        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
