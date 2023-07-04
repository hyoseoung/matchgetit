package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.ManagerSupportStatus;
import com.matchgetit.backend.dto.ManagerSupportRecordDTO;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.entity.ManagerSupportRecordEntity;
import com.matchgetit.backend.entity.MemberEntity;

import com.matchgetit.backend.repository.MemberRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;
    private final MemberService memberService;


    public MemberDTO getUserById(Long userId) {
        MemberEntity memberEntity = memberRepository.findById(userId).orElse(null);
        if (memberEntity != null) {
            return convertToDTO(memberEntity);
        }
        return null;
    }

//    @Scheduled(fixedDelay=10*1000)
//    public void updateMng(){
//        memberService.findManagerUser().forEach(m->createManager1(m));
//    }

    public void updateUser(Long userId, MemberDTO updatedUser) {
        MemberEntity memberEntity = memberRepository.findById(userId).orElse(null);
        if (memberEntity != null) {
            memberEntity.setName(updatedUser.getName());
            memberEntity.setPn(updatedUser.getPn());
            memberEntity.setGender(updatedUser.getGender());
            memberEntity.setManagerSupportStatus(ManagerSupportStatus.valueOf(updatedUser.getManagerSupportStatus()));
            memberEntity.setAccountType(updatedUser.getAccountType());

            // ManagerSupportRecordDTO에서 activityZone 가져와서 설정
            ManagerSupportRecordDTO managerSupportRecordDTO = updatedUser.getManagerSupportRecordDTO();
            if (managerSupportRecordDTO != null) {
                // 기존의 ManagerSupportRecordEntity가 없을 경우 새로 생성
                if (memberEntity.getManagerSupportRecordEntity() == null) {
                    ManagerSupportRecordEntity managerSupportRecordEntity = new ManagerSupportRecordEntity();
                    managerSupportRecordEntity.setActivityZone(managerSupportRecordDTO.getActivityZone());
                    memberEntity.setManagerSupportRecordEntity(managerSupportRecordEntity);
                } else {
                    memberEntity.getManagerSupportRecordEntity().setActivityZone(managerSupportRecordDTO.getActivityZone());
                }
            }

            memberRepository.save(memberEntity);
        }
    }


    private MemberDTO convertToDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setPn(memberEntity.getPn());
        memberDTO.setGender(memberEntity.getGender());
        memberDTO.setManagerSupportStatus(String.valueOf(memberEntity.getManagerSupportStatus()));
        memberDTO.setAccountType(memberEntity.getAccountType());
        // 더 많은 필드 설정이 필요한 경우 여기에 추가

        return memberDTO;
    }
}