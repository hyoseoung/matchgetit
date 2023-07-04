package com.matchgetit.backend.controller;

import com.matchgetit.backend.service.AdminDashboardService;
import com.matchgetit.backend.service.AdminPageUserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminMainController {
    private final AdminDashboardService dashboardService;
    private final AdminPageUserService userService;

    @PostConstruct
    public void createUsers() {
        userService.createUsers();
        dashboardService.createManagers();
    }


    @GetMapping(value = {"/matchGetIt/admin", "/"})
    public String mainPage(Model model) {
        Map<String, Long> userCounts = dashboardService.getUserCounts();
        model.addAttribute("userCounts", userCounts);

        Map<String, Long> managerCounts = dashboardService.getManagerCounts();
        model.addAttribute("managerCounts", managerCounts);

        return "admin/pages/Dashboard/Dashboard";
    }

}
