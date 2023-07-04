package com.matchgetit.backend.controller;

import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.constant.Proficiency;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.request.LoginRequest;
import com.matchgetit.backend.service.KakaoService;
import com.matchgetit.backend.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@AllArgsConstructor
@Controller
@RequestMapping(value="/matchGetIt")
public class KakaoController {
    @Autowired
    private KakaoService kakaoService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/kakao")
    public String kakaoCallback(@RequestParam(name = "code") String code, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        System.out.println("code: " + code);
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        System.out.println("access_Token: " + access_Token);
        System.out.println("nickname : " + userInfo.get("nickname"));
        System.out.println("email : " + userInfo.get("email"));
        System.out.println("gender : " + userInfo.get("gender"));
        MemberDTO member = memberService.findMemberByEmail(String.valueOf(userInfo.get("email")));
        if(member!=null) {
            session.setAttribute("member",member);
            return "redirect:http://localhost:3000";
        } else {
            session.setAttribute("user", userInfo);
            return "addDataKakao";
        }
    }


    @PostMapping("/kakao")
    public String kakaoSignUp(@RequestParam("proficiency") String proficiency,@RequestParam("birthdate") String birthdate, @RequestParam("phoneNumber") String phoneNumber, HttpSession session,HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> userInfo = (HashMap<String, Object>) session.getAttribute("user");
        memberService.socialSignUp(
                (String) userInfo.get("email"),
                (String) userInfo.get("nickname"),
                phoneNumber,
                birthdate,
                (userInfo.get("gender").equals("male") ? Gender.MALE : Gender.FEMALE),
                Proficiency.valueOf(proficiency),
                AccountType.KAKAO
                , LogInType.NORMAL
        );
        MemberDTO member1 = memberService.findMemberByEmail(String.valueOf(userInfo.get("email")));
        session.setAttribute("member", member1);
        return "redirect:http://localhost:3000";

    }
}
