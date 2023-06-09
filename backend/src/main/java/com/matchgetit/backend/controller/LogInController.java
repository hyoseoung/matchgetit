package com.matchgetit.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.matchgetit.backend.request.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

    private final CsrfTokenRepository tokenRepository;

    public LogInController(CsrfTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/matchGetIt/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        System.out.printf(loginRequest.getEmail() + " " + loginRequest.getPassword());
        String savedEmail = "ddddd@naver.com";
        String savedPassword = "12345";

        if (loginRequest.getEmail() != null && loginRequest.getPassword() != null &&
                loginRequest.getEmail().equals(savedEmail) && loginRequest.getPassword().equals(savedPassword)) {
            // 로그인 성공 시 토큰 생성
            CsrfToken token = tokenRepository.generateToken(request);

            // 토큰을 응답 헤더에 설정
            response.setHeader("X-XSRF-TOKEN", token.getToken());

            // 토큰 반환
            return ResponseEntity.ok(token.getToken());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }
    @PostMapping("/matchGetIt/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken token = tokenRepository.loadToken(request);
        if (token != null) {
            tokenRepository.saveToken(null, request, response);
        }

        return ResponseEntity.ok("로그아웃되었습니다.");
    }
}
