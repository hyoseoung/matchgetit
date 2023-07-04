package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.constant.ManagerSupportStatus;
import com.matchgetit.backend.dto.ManagerDTO;
import com.matchgetit.backend.dto.ManagerSupportRecordDTO;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.entity.ManagerEntity;
import com.matchgetit.backend.entity.ManagerSupportRecordEntity;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private ModelMapper modelMapper;
    private final MemberService memberService;

//    @Scheduled(fixedDelay=10*1000)
//    public void updateMng(){
//         memberService.findManagerUser().forEach(m->createManager1(m));
//    }


    public ManagerDTO getManagerById(Long managerId) {
        ManagerEntity managerEntity = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid manager ID: " + managerId));

        return modelMapper.map(managerEntity, ManagerDTO.class);
    }

    public List<ManagerDTO> getAllManagers() {
        List<ManagerEntity> managerEntityEntities = managerRepository.findAll();
        return managerEntityEntities.stream()
                .map(managerEntityEntity -> modelMapper.map(managerEntityEntity, ManagerDTO.class))
                .collect(Collectors.toList());
    }

    public void checkManagerStatus(MemberDTO memberDTO) {
        if (LogInType.MANAGER.equals(memberDTO.getLoginType())) {
            ManagerEntity managerEntity = new ManagerEntity();
            managerEntity.setUser(modelMapper.map(memberDTO, MemberEntity.class));
            // 매니저로 인식되는 경우 추가적인 매니저 정보 설정 가능
            // 예: manager.setManagerImage(userDTO.getManagerImage());
            //     manager.setEmploymentStatus(userDTO.getEmploymentStatus());

            // 매니저 저장
            managerRepository.save(managerEntity);
        }
    }
//    public ManagerDTO updateManager(Long managerId, ManagerDTO updatedManagerDTO) {
//        ManagerEntity managerEntity = managerRepository.findById(managerId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid manager ID: " + managerId));
//
//        // 매니저의 정보 업데이트
//        managerEntity.getUser().setName(updatedManagerDTO.getUser().getName());
//        managerEntity.getUser().setPn(updatedManagerDTO.getUser().getPn());
//        managerEntity.getUser().setGender(updatedManagerDTO.getUser().getGender());
//        managerEntity.setEmploymentStatus(updatedManagerDTO.getEmploymentStatus());
//        managerEntity.setLeaveStartDate(updatedManagerDTO.getLeaveStartDate());
//        managerEntity.setLeaveEndDate(updatedManagerDTO.getLeaveEndDate());
//        managerEntity.setLeaveReason(updatedManagerDTO.getLeaveReason());
//
//        ManagerEntity savedManagerEntity = managerRepository.save(managerEntity);
//        return modelMapper.map(savedManagerEntity, ManagerDTO.class);
//    }

    public ManagerDTO createManager(ManagerDTO managerDTO) {
        ManagerEntity managerEntity = modelMapper.map(managerDTO, ManagerEntity.class);
        ManagerEntity savedManagerEntity = managerRepository.save(managerEntity);
        return modelMapper.map(savedManagerEntity, ManagerDTO.class);
    }

    public void updateManager(Long managerId, ManagerDTO updatedManagerDTO) {
        ManagerEntity managerEntity = managerRepository.findById(managerId).orElse(null);
        if (managerEntity != null) {
            // 매니저의 정보 업데이트
            managerEntity.getUser().setName(updatedManagerDTO.getUser().getName());
            managerEntity.getUser().setPn(updatedManagerDTO.getUser().getPn());
            managerEntity.getUser().setGender(updatedManagerDTO.getUser().getGender());
            managerEntity.setEmploymentStatus(updatedManagerDTO.getEmploymentStatus());
            managerEntity.setLeaveStartDate(updatedManagerDTO.getLeaveStartDate());
            managerEntity.setLeaveEndDate(updatedManagerDTO.getLeaveEndDate());
            managerEntity.setLeaveReason(updatedManagerDTO.getLeaveReason());

            managerRepository.save(managerEntity);
        }
    }

}

//    public void createManager1(MemberDTO memberDTO){
//        ManagerEntity managerEntity = new ManagerEntity();
//        managerEntity.setUser(modelMapper.map(memberDTO,MemberEntity.class));
//        managerEntity.setManagerImage("http://");
//        managerEntity.setEmploymentStatus("");
//        managerEntity.setRegistrationDate(LocalDateTime.now());
//        managerRepository.save(managerEntity);
//
//    }