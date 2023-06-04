package matchgetit.backend.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/MatchGetIt/admin")
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
    public @ResponseBody String editUserInfo() {
        return "유저 정보 수정";
    }

    @GetMapping("/download")
    public @ResponseBody String downloadUserList() {
        return "유저 목록 다운로드 ^^";
    }
}
