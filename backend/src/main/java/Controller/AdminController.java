package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value = "")
    public String mainPage() {
        return "admin/Dashboard";
    }

    @GetMapping("/userList")
    public String userList() {
        return "admin/UserList";
    }

    @GetMapping("/userInfo")
    public String userInfo() {
        return "admin/UserInfo";
    }

    @GetMapping("/editUser")
    public String editUserView() {
        return "admin/UserEdit";
    }

    @PostMapping("/editUser")
    public String editUserInfo() {
        return "redirect:/userInfo";
    }

    @GetMapping("/download")
    public @ResponseBody String downloadUserList() {
        return "유저 목록 다운로드 ^^";
    }
}
