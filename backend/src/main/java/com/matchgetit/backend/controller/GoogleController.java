package com.matchgetit.backend.controller;

import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.constant.Proficiency;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.loginAPI.GoogleUser;
import com.matchgetit.backend.service.GoogleService;
import com.matchgetit.backend.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/matchGetIt")
@RequiredArgsConstructor
public class GoogleController {
    private final MemberService memberService;
    private final GoogleService googleService;

    @GetMapping("/google")
    public String GoogleCallback(@RequestParam(name = "code") String code, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        GoogleUser user = googleService.getGoogleUserInfo(code);
        MemberDTO member = memberService.findMemberByEmail(user.getEmail());
        System.out.println(member);
        if(member!=null){
            session.setAttribute("member",member);
            return "redirect:http://localhost:3000";
        }else {
            session.setAttribute("user", user);
            return "addData2";
        }
    }
    @PostMapping("/google")
    public String signUp(@RequestParam("proficiency") String proficiency, @RequestParam("gender") String gender, @RequestParam("pn") String pn, @RequestParam("birthday") String birthday, HttpSession session) {
        GoogleUser user = (GoogleUser)(session.getAttribute("user"));
        memberService.googleSignUp(
                user.getEmail()
                ,user.getName()
                , String.valueOf(pn)
                , String.valueOf(birthday)
                , Gender.valueOf(gender)
                , Proficiency.valueOf(proficiency)
                , AccountType.GOOGLE
                , LogInType.NORMAL
        );
        MemberDTO member = memberService.findMemberByEmail(user.getEmail());
        session.setAttribute("member",member);

        return "redirect:http://localhost:3000";
    }
}
