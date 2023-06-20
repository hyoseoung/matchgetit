package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.ManagerDTO;
import com.matchgetit.backend.dto.UserDTO;
import com.matchgetit.backend.entity.Manager;
import com.matchgetit.backend.entity.User;
import com.matchgetit.backend.repository.ManagerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ManagerService(ManagerRepository managerRepository, ModelMapper modelMapper) {
        this.managerRepository = managerRepository;
        this.modelMapper = modelMapper;
    }

    public ManagerDTO getManagerById(Long managerId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid manager ID: " + managerId));

        return modelMapper.map(manager, ManagerDTO.class);
    }

    public List<ManagerDTO> getAllManagers() {
        List<Manager> managerEntities = managerRepository.findAll();
        return managerEntities.stream()
                .map(managerEntity -> modelMapper.map(managerEntity, ManagerDTO.class))
                .collect(Collectors.toList());
    }

    public void checkManagerStatus(UserDTO userDTO) {
        if ("3".equals(userDTO.getAccountState())) {
            Manager manager = new Manager();
            manager.setUser(modelMapper.map(userDTO, User.class));
            // 매니저로 인식되는 경우 추가적인 매니저 정보 설정 가능
            // 예: manager.setManagerImage(userDTO.getManagerImage());
            //     manager.setEmploymentStatus(userDTO.getEmploymentStatus());

            // 매니저 저장
            managerRepository.save(manager);
        }
    }
    public ManagerDTO updateManager(Long managerId, ManagerDTO updatedManagerDTO) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid manager ID: " + managerId));

        // 매니저의 정보 업데이트
        manager.getUser().setName(updatedManagerDTO.getUser().getName());
        manager.getUser().setPhoneNum(updatedManagerDTO.getUser().getPn());
        manager.getUser().setGender(updatedManagerDTO.getUser().getGender());
        manager.setEmploymentStatus(updatedManagerDTO.getEmploymentStatus());
        manager.setLeaveStartDate(updatedManagerDTO.getLeaveStartDate());
        manager.setLeaveEndDate(updatedManagerDTO.getLeaveEndDate());
        manager.setLeaveReason(updatedManagerDTO.getLeaveReason());

        Manager savedManager = managerRepository.save(manager);
        return modelMapper.map(savedManager, ManagerDTO.class);
    }
}
