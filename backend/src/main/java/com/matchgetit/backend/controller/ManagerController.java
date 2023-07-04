package com.matchgetit.backend.controller;

import com.matchgetit.backend.constant.*;
import com.matchgetit.backend.dto.ManagerDTO;
import com.matchgetit.backend.dto.ManagerSupportRecordDTO;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.entity.ManagerEntity;
import com.matchgetit.backend.entity.ManagerSupportRecordEntity;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.repository.ManagerRepository;
import com.matchgetit.backend.repository.ManagerSupportRecordRepository;
import com.matchgetit.backend.repository.MemberRepository;
import com.matchgetit.backend.service.ManagerService;
import com.matchgetit.backend.service.MemberService;
import com.matchgetit.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Controller
@RequestMapping("/matchGetIt/manager")
@AllArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    private final ManagerSupportRecordRepository managerSupportRecordRepository;
    private final UserService userService; // UserService 인스턴스 추가
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final ManagerRepository managerRepository;
    private final MemberService memberService;



    @GetMapping("/managers/test")
    public List<ManagerEntity> getAllMembers() {
        return managerRepository.findAll();
    }

    @GetMapping("/managers")
    public String getManagerList(HttpServletRequest request) {
        List<MemberEntity> managerList = memberRepository.findByLoginType(LogInType.MANAGER);
        List<MemberDTO> memberDTOList = new ArrayList<>();


        for (MemberEntity memberEntity : managerList) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserId(memberEntity.getUserId());
            memberDTO.setName(memberEntity.getName());
            memberDTO.setPn(memberEntity.getPn());

            ManagerEntity manager = memberEntity.getManagerEntity();
            if(manager != null){
                String managerImage = manager.getManagerImage();

                ManagerDTO managerDTO = new ManagerDTO();
                managerDTO.setManagerImage(managerImage);

                memberDTO.setManagerDTO(managerDTO);
            }
            memberDTOList.add(memberDTO);
        }

        request.setAttribute("managerList", memberDTOList);
        return "admin/pages/Manage/Manager";
    }

    //매니저 삭제
    @PutMapping("/deactivate/{userId}")
    public ResponseEntity<String> deactivateManager(@PathVariable Long userId) {
        MemberEntity memberEntity = memberRepository.findById(userId).orElse(null);
        if (memberEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        System.out.println("2323");
        // 매니저 지원 레코드 초기화
        ManagerSupportRecordEntity managerSupportRecordEntity = memberEntity.getManagerSupportRecordEntity();
        if (managerSupportRecordEntity != null) {
            memberEntity.setManagerSupportRecordEntity(null);
            managerSupportRecordEntity.setManagerUser(null);
            memberRepository.save(memberEntity);
            managerSupportRecordRepository.delete(managerSupportRecordEntity);
        }

        // 매니저 삭제
        ManagerEntity managerEntity = memberEntity.getManagerEntity();
        if (managerEntity != null) {
            memberEntity.setManagerEntity(null);
            managerEntity.setUser(null);
            memberRepository.save(memberEntity);
            managerRepository.delete(managerEntity);
        }

        // 거절 처리
        memberEntity.setLoginType(LogInType.NORMAL);
        memberRepository.save(memberEntity);

        return ResponseEntity.ok("매니저 등록을 거절하였습니다.");
    }

    //매니저 정보수정 (휴직신청때 필요)
    @PutMapping("/manager/EditManager/updateManager/{managerId}")
    public ResponseEntity<String> updateManager(@PathVariable("managerId") Long managerId, @RequestBody ManagerDTO updatedManager) {
        ManagerDTO managerDTO = managerService.getManagerById(managerId);
        System.out.println("!");

        if (managerDTO != null) {
            // 수정된 매니저 정보 업데이트
            managerDTO.getUser().setName(updatedManager.getUser().getName());
            managerDTO.getUser().setPn(updatedManager.getUser().getPn());
            managerDTO.getUser().setGender(updatedManager.getUser().getGender());
            managerDTO.setEmploymentStatus(updatedManager.getEmploymentStatus());

            if (updatedManager.getEmploymentStatus().equals("leave")) {
                managerDTO.setLeaveStartDate(updatedManager.getLeaveStartDate());
                managerDTO.setLeaveEndDate(updatedManager.getLeaveEndDate());
                managerDTO.setLeaveReason(updatedManager.getLeaveReason());
            } else {
                managerDTO.setLeaveStartDate(null);
                managerDTO.setLeaveEndDate(null);
                managerDTO.setLeaveReason(null);
            }

            // ManagerDTO를 Manager Entity로 변환
            ManagerEntity managerEntity = convertToManagerEntity(managerDTO);

            managerService.updateManager(managerId, updatedManager);

            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ManagerEntity convertToManagerEntity(ManagerDTO managerDTO) {
        ManagerEntity managerEntity = new ManagerEntity();
        managerEntity.setManagerId(managerDTO.getManagerId());
        managerEntity.setUser(managerDTO.getUser());
        managerEntity.setStadiumId(managerDTO.getStadiumId());
        managerEntity.setPartyId(managerDTO.getPartyId());
        managerEntity.setStartTime(managerDTO.getStartTime());
        managerEntity.setTeam(managerDTO.getTeam());
        managerEntity.setMatchType(managerDTO.getMatchType());
        managerEntity.setManagerImage(managerDTO.getManagerImage());
        managerEntity.setRegistrationDate(managerDTO.getRegistrationDate());
        managerEntity.setEmploymentStatus(managerDTO.getEmploymentStatus());
        managerEntity.setLeaveStartDate(managerDTO.getLeaveStartDate());
        managerEntity.setLeaveEndDate(managerDTO.getLeaveEndDate());
        managerEntity.setLeaveReason(managerDTO.getLeaveReason());

        return managerEntity;
    }

    //일반유저가 매니저 신청때 사용되는 메서드
    @PostMapping("/submitForm/{userId}")
    public ResponseEntity<String> submitForm(@PathVariable("userId") Long userId, @RequestBody ManagerSupportRecordDTO formDTO) {
        MemberEntity memberEntity = memberRepository.findById(userId).orElse(null);

        if (memberEntity != null) {
            memberEntity.setManagerSupportStatus(ManagerSupportStatus.WAITING);
            memberRepository.save(memberEntity);

        }

        // 매니저 지원 양식 제출 처리
        ManagerSupportRecordEntity supportRecord = new ManagerSupportRecordEntity();
        supportRecord.setManagerUser(memberEntity);
        supportRecord.setActivityZone(formDTO.getActivityZone());
        supportRecord.setSubmissionDate(new Date()); // 현재 날짜와 시간으로 설정

        // supportRecord를 DB에 저장
        managerSupportRecordRepository.save(supportRecord);

        return ResponseEntity.ok("매니저지원이 완료됬습니다.");
    }

    @GetMapping("/managerInfo/{userId}")
    public String viewManagerDetails(@PathVariable Long userId, Model model) {
        MemberDTO member = memberService.findMemberById(userId);

        ManagerDTO managerDTO = new ManagerDTO();
        ManagerEntity managerEntity = member.getManagerEntity();
        if (managerEntity != null){
            managerDTO.setManagerId(managerEntity.getManagerId());
            managerDTO.setEmploymentStatus(managerEntity.getEmploymentStatus());
            managerDTO.setRegistrationDate(managerEntity.getRegistrationDate());
        }
        member.setManagerDTO(managerDTO);

        model.addAttribute("manager", member);
        return "admin/pages/Manage/ManagerInfo";
    }
    @GetMapping("/EditManager/{userId}")
    public String editManager (@PathVariable Long userId, Model model){
        MemberDTO member = memberService.findMemberById(userId);

        ManagerDTO managerDTO = new ManagerDTO();
        ManagerEntity managerEntity = member.getManagerEntity();
        if (managerEntity != null){
            managerDTO.setManagerId(managerEntity.getManagerId());
            managerDTO.setEmploymentStatus(managerEntity.getEmploymentStatus());
            managerDTO.setRegistrationDate(managerEntity.getRegistrationDate());
            System.out.println(managerEntity.getEmploymentStatus());
        }
        member.setManagerDTO(managerDTO);
        managerDTO.getEmploymentStatus().name();
        model.addAttribute("manager", member);

        return "admin/pages/Manage/EditManager";
    }
}
