package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyEntity;
import com.matchgetit.backend.repository.PartyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PartyService {
    private final PartyRepository partyRepository;
    private final MemberService memberService;

    public PartyDTO createParty(int partyLeaderId) {
        PartyEntity existingParty = partyRepository.findByPartyLeader(partyLeaderId);
        PartyEntity partyEntity = new PartyEntity();
        ModelMapper modelMapper = new ModelMapper();
        if (existingParty != null) {
            throw new IllegalArgumentException("이미 해당 파티가 존재합니다.");
        }
        partyEntity.setPartyLeader(partyLeaderId);
        partyRepository.save(partyEntity);
        return modelMapper.map(partyEntity,PartyDTO.class);
    }

    public PartyDTO getPartyByLeaderId(int partyLeaderId) {
        PartyEntity party = partyRepository.findByPartyLeader(partyLeaderId);
        if (party == null) {
            throw new IllegalArgumentException("해당 파티가 존재하지 않습니다.");
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(party,PartyDTO.class);
    }

    public PartyDTO getPartyByMemberId(int partyMemberId) {
        PartyEntity party = partyRepository.findByMemberId(partyMemberId);
        if (party == null) {
            throw new IllegalArgumentException("해당 파티가 존재하지 않습니다.");
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(party,PartyDTO.class);
    }
    public List<MemberDTO> getPartyMembers(int partyMemberId){
        MemberDTO member = memberService.findMemberById(partyMemberId);
        List<MemberEntity> memberEntities = partyRepository.findPartyMembers(member.getPartyEntity());
        List<MemberDTO> members =new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        memberEntities.forEach(m->members.add(modelMapper.map(m,MemberDTO.class)));
        return members;
    }

    public void deleteParty(int partyId) {
        PartyEntity party = partyRepository.findByPartyId(partyId);
        if (party == null) {
            throw new IllegalArgumentException("해당 파티가 존재하지 않습니다.");
        }
        partyRepository.delete(party);
    }
}
