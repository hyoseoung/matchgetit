package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.GameType;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyEntity;
import com.matchgetit.backend.repository.MemberRepository;
import com.matchgetit.backend.repository.PartyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PartyService {
    private final PartyRepository partyRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public void createParty(Long partyLeaderId,String address, String x, String y, Date date, String time, GameType gameType) {
        MemberEntity partyLeader = memberRepository.findByUserId(partyLeaderId);
        PartyEntity existingParty = partyRepository.findByPartyLeader(partyLeader);
        PartyEntity partyEntity = new PartyEntity();
        if (existingParty != null) {
            throw new IllegalArgumentException("이미 해당 파티가 존재합니다.");
        }
        partyEntity.setPartyLeader(partyLeader);
        partyEntity.setAddress(address);
        partyEntity.setLocationX(x);
        partyEntity.setLocationY(y);
        partyEntity.setApplicationDate(date);
        partyEntity.setApplicationTime(time);
        partyEntity.setPartyApplicationDate(new Date());
        partyEntity.setGameType(String.valueOf(Objects.equals(gameType.toString(), "FRIENDLY") ? GameType.FRIENDLY:GameType.LEAGUE));
        partyRepository.save(partyEntity);
    }
    public List<MemberDTO> getPartyMembers(Long partyMemberId){
        MemberDTO member = memberService.findMemberById(partyMemberId);
        if(member.getParty()!=null){
            PartyEntity party = partyRepository.findPartyWithMembers(modelMapper.map(member.getParty(),PartyEntity.class));
            System.out.println(party.getPartyId());
            party.getMembers().forEach(e-> System.out.println("파티원:"+e.getEmail()));
            return party.getMembers().stream().map(m->modelMapper.map(m,MemberDTO.class)).toList();
        }else{
            return new ArrayList<>();
        }
    }
    //    public PartyDTO getParty(Long userId){
//        MemberEntity member = memberRepository.findByUserId(userId);
//        PartyEntity party = partyRepository.findByPartyLeader(member.getParty().getPartyLeader());
//        if(party == null)return null;
//        else{
//            return modelMapper.map(party,PartyDTO.class);
//        }
//    }
    public PartyDTO findPartyByPartyLeaderId(Long partyLeaderId){
        MemberEntity partyLeader = memberRepository.findByUserId(partyLeaderId);
        PartyEntity party = partyRepository.findByPartyLeader(partyLeader);
        return modelMapper.map(party,PartyDTO.class);
    }
    @Transactional
    public void deleteParty(Long partyId) {
        PartyEntity party = partyRepository.findByPartyId(partyId);
        party = partyRepository.findPartyWithMembers(party);
        if (party == null) {
            throw new IllegalArgumentException("해당 파티가 존재하지 않습니다.");
        }
        List<MemberDTO> memberList= party.getMembers().stream().map(m->modelMapper.map(m,MemberDTO.class)).toList();
        memberList.forEach(m->memberService.deleteParty(m.getUserId()));
        partyRepository.delete(party);
    } //나중에 매칭이 완전히 끝났을 경우 대비 메소드
    public void updatePartyCount(PartyDTO partyDTO,Long count){
        PartyEntity party = partyRepository.findByPartyId(partyDTO.getPartyId());
        party.setCount(count);
        partyRepository.save(party);
    }
    public void updatePartyRatingAvg(PartyDTO partyDTO){
        if(partyDTO!=null){
            PartyEntity party = partyRepository.findByPartyId(partyDTO.getPartyId());
            party.setPartyRatingAvg();
            partyRepository.save(party);
        }
    }
}
