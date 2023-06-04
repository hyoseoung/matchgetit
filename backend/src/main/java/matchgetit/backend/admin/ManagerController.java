package matchgetit.backend.admin;

import org.apache.catalina.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ManagerController {

    @GetMapping("/managers")
    public String getManagers(Model model) {
        List<Manager> managerList = createDummyManagers(); // 더미 매니저 데이터 생성
        model.addAttribute("managerList", managerList);
        return "manager-page";
    }

    private List<Manager> createDummyManagers() {
        List<Manager> managerList = new ArrayList<>();

        // 더미 매니저 데이터 생성
        managerList.add(new Manager("John Doe", "010-1234-5678", "Game 1"));
        managerList.add(new Manager("Jane Smith", "010-9876-5432", "Game 2"));
        managerList.add(new Manager("David Johnson", "010-5555-7777", "Game 3"));
        // 여러 개의 더미 매니저 데이터 추가...

        return managerList;
    }

    // 다른 메서드와 클래스 구현...
}
