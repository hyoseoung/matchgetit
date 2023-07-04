package com.matchgetit.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchgetit.backend.config.JwtTokenProvider;
import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.constant.Proficiency;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.loginAPI.NaverUser;
import com.matchgetit.backend.repository.MemberRepository;
import com.matchgetit.backend.service.MemberService;
import com.matchgetit.backend.service.NaverService;
import jakarta.servlet.http.Cookie;
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

@Controller
@RequestMapping("/matchGetIt")
@AllArgsConstructor
public class NaverController {
    private final MemberService memberService;
    private final NaverService naverService;

    @GetMapping("/naver")
    public String naverCallback(@RequestParam(name = "code") String code, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        NaverUser user = naverService.getNaverUserInfo(code);
        MemberDTO member = memberService.findMemberByEmail(user.getEmail());
        if(member!=null){
            session.setAttribute("member",member);
            return "redirect:http://localhost:3000";
        }else {
            session.setAttribute("user", user);
            return "addData";
        }
    }
    @PostMapping("/naver")
    public String signUp(@RequestParam("proficiency") String proficiency,HttpSession session) {
        NaverUser user = (NaverUser)(session.getAttribute("user"));
        memberService.socialSignUp(
                user.getEmail()
                ,user.getName()
                ,user.getMobile()
                , user.getBirthyear()+"-"+ user.getBirthday()
                ,(user.getGender().equals("M")? Gender.MALE:Gender.FEMALE)
                , Proficiency.valueOf(proficiency)
                , AccountType.NAVER
                , LogInType.NORMAL
        );
        MemberDTO member = memberService.findMemberByEmail(user.getEmail());
        session.setAttribute("member",member);
        return "redirect:http://localhost:3000";
    }
}
