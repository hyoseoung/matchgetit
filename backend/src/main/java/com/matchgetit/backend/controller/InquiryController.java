package com.matchgetit.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MatchGetIt/admin")
public class InquiryController {

    @GetMapping("/inquiryBoard")
    public String board() {
        return "inquiry/InquiryBoard";
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String view(@PathVariable String inquiryId) {
        return "inquiry/InquiryPost";
    }

    @GetMapping("/test")
    public String test() {
        return "inquiry/temp5";
    }

}
