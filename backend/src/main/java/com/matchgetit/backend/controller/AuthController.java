package com.matchgetit.backend.controller;
import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.request.SignUpRequest;
import com.matchgetit.backend.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.matchgetit.backend.request.LoginRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matchGetIt/auth")
@AllArgsConstructor
public class AuthController {
    private final CsrfTokenRepository tokenRepository;
    private MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, HttpSession session, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // 유효성 검사 오류가 있는 경우 처리
                StringBuilder errorReason = new StringBuilder();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorReason.append(error.getDefaultMessage()).append("\n");
                }
                return new ResponseEntity<>(errorReason.toString(), HttpStatus.BAD_REQUEST);
            }
            MemberDTO member = memberService.login(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );
            CsrfToken token = tokenRepository.generateToken(request);
            response.setHeader("X-XSRF-TOKEN", token.getToken());
            session.setAttribute("member",member);
            return new ResponseEntity<>("로그인에 성공하였습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("member");
        tokenRepository.saveToken(null, request, response);
        return ResponseEntity.ok("로그아웃되었습니다.");
    }
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있는 경우 처리
            StringBuilder errorReason = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorReason.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorReason.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            memberService.signUp(
                    signUpRequest.getEmail(),
                    signUpRequest.getPassword(),
                    signUpRequest.getName(),
                    signUpRequest.getPn(),
                    signUpRequest.getBirthDay(),
                    signUpRequest.getGender(),
                    signUpRequest.getProficiency(),
                    AccountType.NORMAL
            );
            return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/session")
    public ResponseEntity<MemberDTO> getUserProfile(HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    @PostMapping("/vaildateEmail")
    public ResponseEntity<String> validateEmail(@RequestParam String email){
        MemberDTO dto=  memberService.findMemberByEmail(email);
        if(dto!=null){
            return new ResponseEntity<>("불가능",HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<>("가능", HttpStatus.OK);
        }
    }
    @PostMapping("/validatePhoneNumber")
    public ResponseEntity<String> validatePhoneNumber(@RequestParam String phoneNumber){
        MemberDTO dto=  memberService.findMemberByPhoneNumber(phoneNumber);
        if((dto==null)&&(phoneNumber.matches("^\\d{3}-\\d{4}-\\d{4}$"))){
            return new ResponseEntity<>("가능", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("불가능",HttpStatus.BAD_REQUEST);
        }
    }
}
