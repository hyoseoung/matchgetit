package com.matchgetit.backend.controller;

import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matchGetIt/pay")
@AllArgsConstructor
public class PayController {
    private MemberService memberService;

    @GetMapping("/payStart/{payment}")
    public String startPay(@PathVariable int payment, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        session.setAttribute("pay",payment);
        System.out.println(payment);
        model.addAttribute("user", member);
        model.addAttribute("payment", payment);
        // member.getName() 이름 필드 member.
        return "tossPay"; // tossPay.html 템플릿 파일을 반환하도록 수정
    }

    @GetMapping("/success")
    public String successPay(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        int value =(Integer) session.getAttribute("pay");
        memberService.updateCredit(member.getUserId(),value);
        System.out.println(value);
        session.removeAttribute("pay");
        return "redirect:http://localhost:3000";
    }
    @GetMapping("/fail")
    public String failPay(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("pay");
        return "redirect:http://localhost:3000";
    }
}

