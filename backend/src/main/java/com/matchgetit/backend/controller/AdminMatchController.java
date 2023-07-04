package com.matchgetit.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matchGetIt/admin")
@RequiredArgsConstructor
public class AdminMatchController {

    private final String path = "admin/pages/Match/";
//    private final String alertViewPath = "admin/components/Utils/alert";

    @GetMapping({"/matchList", "/matchList/{page}"})
    public String matchList() {
        return path + "MatchList";
    }

    @GetMapping("matchInfo")
    public String matchInfo() {
        return path + "MatchInfo";
    }

}
