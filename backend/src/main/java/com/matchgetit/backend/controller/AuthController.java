package com.matchgetit.backend.controller;

import com.matchgetit.backend.entity.MatchUserEntity;
import com.matchgetit.backend.request.SignUpRequest;
import com.matchgetit.backend.service.MatchUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.matchgetit.backend.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matchGetIt/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private MatchUserService memberService;
    private final CsrfTokenRepository tokenRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            MatchUserEntity member = memberService.login(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );
            CsrfToken token = tokenRepository.generateToken(request);
            response.setHeader("X-XSRF-TOKEN", token.getToken());
            return new ResponseEntity<>("로그인에 성공하였습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken token = tokenRepository.loadToken(request);
        if (token != null) {
            tokenRepository.saveToken(null, request, response);
        }

        return ResponseEntity.ok("로그아웃되었습니다.");
    }
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            memberService.signUp(
                    signUpRequest.getEmail(),
                    signUpRequest.getPassword(),
                    signUpRequest.getName(),
                    signUpRequest.getPn(),
                    signUpRequest.getGender(),
                    signUpRequest.getProficiency()
            );
            return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
