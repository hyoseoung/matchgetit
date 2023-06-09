package com.matchgetit.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import com.matchgetit.backend.dto.AdminPageUserDTO;
import com.matchgetit.backend.entity.User;
import com.matchgetit.backend.service.AdminPageUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminPageUserService userService;

    @GetMapping(value = "")
    public String mainPage() {
        return "admin/Dashboard";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "admin/UserList";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        AdminPageUserDTO userDto = userService.getUserInfo(Long.valueOf(userId));
        model.addAttribute("user", userDto);
        return "admin/UserInfo";
    }

    @GetMapping("/editUser")
    public String editUserView(Model model, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        AdminPageUserDTO userDto = userService.getUserInfo(Long.valueOf(userId));
        model.addAttribute("user", userDto);
        return "admin/UserEdit";
    }

    @PostMapping("/editUser")
    public String editUserInfo(AdminPageUserDTO userDto) {
        Long userId = userService.updateUserInfo(userDto);
        return "redirect:/MatchGetIt/admin/userInfo?userId="+userId;
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(Model model, @PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/MatchGetIt/admin/userList";
    }

    @GetMapping("/download")
    public @ResponseBody String downloadUserList() {
        return "유저 목록 다운로드 ^^";
    }
}
