package com.matchgetit.backend.controller;

import com.matchgetit.backend.dto.ManagerDTO;
import com.matchgetit.backend.dto.UserDTO;
import com.matchgetit.backend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers")
    public String getManagerList(Model model) {
        // 매니저로 인식되는 처리 로직 호출
        UserDTO userDTO = new UserDTO(); // 예시로 사용자 정보 생성
        userDTO.setAccountState("3"); // 예시로 accountState를 "3"으로 설정
        managerService.checkManagerStatus(userDTO);

        // 매니저 리스트 조회
        List<ManagerDTO> managerList = managerService.getAllManagers();

        model.addAttribute("managerList", managerList);
        return "manager-list";
    }

    @PutMapping("/api/manager/{managerId}/deactivate")
    public ResponseEntity<String> deactivateManager(@PathVariable Long managerId) {
        ManagerDTO managerDTO = managerService.getManagerById(managerId);
        if (managerDTO != null) {
            managerDTO.getUser().setAccountState("1"); // accountState 값을 "1"로 변경
            managerService.updateManager(managerId, managerDTO); // 변경된 매니저 정보 업데이트
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/api/manager/{managerId}")
    public ResponseEntity<String> updateManager(@PathVariable("managerId") Long managerId, @RequestBody ManagerDTO updatedManager) {
        ManagerDTO managerDTO = managerService.getManagerById(managerId);
        if (managerDTO != null) {
            // 수정된 매니저 정보 업데이트
            managerDTO.getUser().setName(updatedManager.getUser().getName());
            managerDTO.getUser().setPn(updatedManager.getUser().getPn());
            managerDTO.getUser().setGender(updatedManager.getUser().getGender());
            managerDTO.setEmploymentStatus(updatedManager.getEmploymentStatus());
            managerDTO.setLeaveStartDate(updatedManager.getLeaveStartDate());
            managerDTO.setLeaveEndDate(updatedManager.getLeaveEndDate());
            managerDTO.setLeaveReason(updatedManager.getLeaveReason());

            managerService.updateManager(managerId, managerDTO); // 변경된 매니저 정보 업데이트
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
