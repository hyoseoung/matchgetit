package com.matchgetit.backend.service;
import com.matchgetit.backend.constant.AcceptType;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyAcceptDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyAcceptEntity;
import com.matchgetit.backend.entity.PartyEntity;
import com.matchgetit.backend.repository.MemberRepository;
import com.matchgetit.backend.repository.PartyAcceptRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.lang.reflect.Member;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PartyAcceptService {
    private final PartyAcceptRepository partyAcceptRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void cleanup() {
        List<PartyAcceptEntity> entities = partyAcceptRepository.findAll();
        Date oneHourAgo = new Date(System.currentTimeMillis() - 60 * 60 * 1000);

        for (PartyAcceptEntity entity : entities) {
            if (entity.getOverTime().before(oneHourAgo)) {
                partyAcceptRepository.delete(entity);
            }
        }
    } //1시간 지나면 일괄 삭제 메소드

    public void createPartyAccept(Long partyLeaderId,Long userId){
        MemberEntity member = memberRepository.findByUserId(userId);
        MemberEntity partyLeader= memberRepository.findByUserId(partyLeaderId);
        PartyAcceptEntity partyAcceptEntity = new PartyAcceptEntity();
        partyAcceptEntity.setPartyLeader(partyLeader);
        partyAcceptEntity.setAgreement(AcceptType.WAIT);
        partyAcceptEntity.setUser(member);
        partyAcceptEntity.setOverTime(new Date());
        partyAcceptRepository.save(partyAcceptEntity);
    } // 파티초대 엔티티 생성
    public List<PartyAcceptDTO> getPartyAccept(Long userId){
        MemberEntity member= memberRepository.findByUserId(userId);
        List<PartyAcceptEntity> partyAcceptEntities= partyAcceptRepository.findByUser(member);
        if(partyAcceptEntities.isEmpty()) return null;
        List<PartyAcceptDTO> partyAccepts= partyAcceptEntities.stream().map(m->modelMapper.map(m,PartyAcceptDTO.class)).toList();
        return partyAccepts;
    }//userId로 파티초대 엔티티 찾기
    public List<PartyAcceptDTO> getPartyAcceptByPartyLeaderId(Long partyLeaderId){
        MemberEntity partyLeader = memberRepository.findByUserId(partyLeaderId);
        List<PartyAcceptEntity> partyAcceptEntities= partyAcceptRepository.findByPartyLeader(partyLeader);
        if(partyAcceptEntities.isEmpty()) return null;
        List<PartyAcceptDTO> partyAccepts= partyAcceptEntities.stream().map(m->modelMapper.map(m,PartyAcceptDTO.class)).toList();
        return partyAccepts;
    }//파티장id로 파티초대 엔티티 찾기
    public PartyAcceptDTO getPartyAcceptByUserIdAndPartyLeaderId(Long userId,Long partyLeaderId){
        MemberEntity partyLeader = memberRepository.findByUserId(partyLeaderId);
        MemberEntity member = memberRepository.findByUserId(userId);
        PartyAcceptEntity partyAcceptEntity= partyAcceptRepository.findByUserAndPartyLeader(member,partyLeader);
        if(partyAcceptEntity ==null) return null;
        else return modelMapper.map(partyAcceptEntity, PartyAcceptDTO.class);
    };//유저,파티장 id로 해당 엔티티를 찾아서 리턴
    public void updatePartyAcceptStatus(Long partyAcceptId, AcceptType agreement) {
        PartyAcceptEntity partyAcceptEntity = partyAcceptRepository.findByPartyAcceptId(partyAcceptId);
        partyAcceptEntity.setAgreement(agreement);
        partyAcceptRepository.save(partyAcceptEntity);
    }//수락/거절 의사 칼럼 업데이트


    @Transactional
    public void deletePartyAcceptByPartyLeaderId(Long partyLeaderId) {
        MemberEntity partyLeader= memberRepository.findByUserId(partyLeaderId);
        partyAcceptRepository.deleteByPartyLeader(partyLeader);
    }//파티 테이블에 추가 시 일괄 삭제
    @Transactional
    public void deletePartyAcceptByPartyLeaderIdAndPartyMemberId(Long partyLeaderId,Long userId) {
        MemberEntity partyLeader= memberRepository.findByUserId(partyLeaderId);
        MemberEntity partyMember=memberRepository.findByUserId(userId);
        partyAcceptRepository.deleteByPartyLeaderAndUser(partyLeader,partyMember);
    }
}
