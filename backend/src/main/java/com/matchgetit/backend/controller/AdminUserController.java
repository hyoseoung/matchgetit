package com.matchgetit.backend.controller;

import com.matchgetit.backend.dto.AdminPageSearchUserDTO;
import com.matchgetit.backend.dto.AdminPageUserDTO;
import com.matchgetit.backend.service.AdminPageUserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/matchGetIt/admin")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminPageUserService userService;

    private final String path = "admin/pages/User/";
    private final String alertViewPath = "admin/components/Utils/alert";

    @PostConstruct
    public void createUsers() {
        userService.createUsers();
    }

//    @GetMapping(value = "")
//    public String mainPage(Model model) {
//        return "admin/Dashboard";
//    }


    // 유저 목록 조회 페이지
    @GetMapping({"/userList", "/userList/{page}"})
    public String userList(Model model, @PathVariable("page") Optional<Integer> page, AdminPageSearchUserDTO searchUserDTO) {
//        String temp = request.getParameter("pageSize");
        Integer temp = searchUserDTO.getPageSize();
        int pageSize = temp == null ? 5 : temp;

        Pageable pageable = PageRequest.of(page.orElse(0), pageSize);
        Page<AdminPageUserDTO> userList = userService.getPageableUserList(searchUserDTO, pageable);

//        List<UserEntity> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        model.addAttribute("currPageNum", pageable.getPageNumber());
        model.addAttribute("searchUserDTO", searchUserDTO);
        return path + "UserList";
    }

    // 선택한 유저 삭제
    @DeleteMapping("/userList")
    public @ResponseBody ResponseEntity<String> deleteUsers(@RequestParam(value = "arr[]") String[] users) {
        for (String userId: users) {
            userService.deleteUser(Long.valueOf(userId));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // 개별 유저 조회 페이지
    @GetMapping("/userInfo")
    public String userInfo(Model model, @RequestParam Long userId) {
        try {
            AdminPageUserDTO userDto = userService.getUserInfo(userId);
            model.addAttribute("user", userDto);
            return path + "UserInfo";
        }
        catch (EntityNotFoundException e) {
            model.addAttribute("msg", "존재하지 않는 회원입니다.");
            model.addAttribute("url", "/matchGetIt/admin/userList");
            return alertViewPath;
        }
    }

//    @GetMapping("/userInfo/{userId}")
//    public String userInfo(Model model, @PathVariable("userId") Long userId) {
//        AdminPageUserDTO userDto = userService.getUserInfo(userId);
//        model.addAttribute("user", userDto);
//        return "admin/UserInfo";
//    }


    // 유저 정보 수정 페이지
    @GetMapping("/editUser")
    public String editUserView(Model model, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        AdminPageUserDTO userDto = userService.getUserInfo(Long.valueOf(userId));
        model.addAttribute("user", userDto);
        return path + "UserEdit";
    }

    // 수정 submit시 수정 사항 반영
    @PostMapping("/editUser")
    public String editUserInfo(AdminPageUserDTO userDto) {
//        System.out.println(userDto);
        Long userId = userService.updateUserInfo(userDto);
        return "redirect:/matchGetIt/admin/userInfo?userId="+userId;
    }

    // 유저 정보 조회 페이지에서 유저 삭제 버튼 눌렀을 때
    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(Model model, @PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
//        return "redirect:/matchGetIt/admin/userList";
        model.addAttribute("msg", "삭제하였습니다.");
        model.addAttribute("url", "/matchGetIt/admin/userList");
        return alertViewPath;
    }


    // 유저 목록 엑셀 다운로드 (미구현)
    @GetMapping("/download")
    public @ResponseBody String downloadUserList() {
        return "유저 목록 다운로드 ^^";
    }


    @GetMapping("/banUser/{userId}")
    public String banUser(@PathVariable("userId") Long userId) {
        return path + "UserBan";
    }
}
