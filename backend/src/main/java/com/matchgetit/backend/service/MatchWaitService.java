package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.MatchWaitDTO;
import com.matchgetit.backend.dto.StadiumDTO;
import com.matchgetit.backend.entity.*;
import com.matchgetit.backend.repository.*;
import com.matchgetit.backend.util.NearStadium;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MatchWaitService{
    private final MemberRepository memberRepository;
    private final MatchWaitRepository matchWaitRepository;
    private final ModelMapper modelMapper;
    private final StadiumRepository stadiumRepository;
    private final MatchRepository matchRepository;
    public List<MatchWaitDTO> findMatchListByMemberId(Long userId){
        MemberEntity member =memberRepository.findByUserId(userId);
        MatchWaitEntity matchWait = matchWaitRepository.findByMember(member);
        PartyEntity party = matchWait.getParty();
        System.out.println(party.getPartyId());
        System.out.println(party.getCount());
        System.out.println(party.getGameType());
        System.out.println(party.getLocationX());
        System.out.println(matchWait.getStadium().getStdName());
        System.out.println(matchWait.getMatchWaitId());
        System.out.println(matchWait.getTeam());
        List<MatchWaitEntity> matchWaitList = matchWaitRepository.findByStadiumAndParty_ApplicationTimeAndParty_ApplicationDate(matchWait.getStadium(), party.getApplicationTime(), party.getApplicationDate());
        return matchWaitList.stream().map(m->modelMapper.map(m,MatchWaitDTO.class)).toList();
    }
    public boolean validMatch(String x, String y, Date applicationDate, String applicationTime){
        List<StadiumEntity> stdList = stadiumRepository.findAll();
        StadiumEntity stadium = NearStadium.findNearestStadiums(stdList,x,y,1);
        List<MatchWaitEntity> matchWaitList = matchWaitRepository.findByStadiumAndParty_ApplicationTimeAndParty_ApplicationDate(stadium, applicationTime, applicationDate);
        return (matchWaitList!=null)?false:true;
    }
    public MatchWaitDTO findMatchWaitByMemberId(Long userId){
        MemberEntity member = memberRepository.findByUserId(userId);
        MatchWaitEntity matchWait = matchWaitRepository.findByMember(member);
        return (matchWait!=null)?modelMapper.map(matchWait,MatchWaitDTO.class):null;
    }
    public void deleteMatchWait( MatchWaitDTO matchWait){
        //취소한 매치웨이트를 제외한 매치 웨이트들을 매치로 다시 넣는 과정
        MatchWaitEntity matchWaitEntity = modelMapper.map(matchWait,MatchWaitEntity.class);
        StadiumEntity stadium = matchWaitEntity.getStadium();
        List<MatchWaitEntity> matchWaitList=matchWaitRepository
                .findByStadiumAndParty_ApplicationTimeAndParty_ApplicationDate(
                        stadium
                        ,matchWait.getParty().getApplicationTime()
                        ,matchWait.getParty().getApplicationDate());
        matchWaitList.forEach(m-> System.out.println("삭제해야 될 매치웨이트: "+m.getMember().getName()));
        //시간 장소가 일치하는 모임들을 찾아 match테이블에 할당
        matchWaitList.stream()
                .filter(m->m.getParty().equals(matchWaitEntity.getParty().getPartyId()))
                .forEach(m->matchWaitRepository.delete(m)); //같은 파티원 제거
        matchWaitList
                .stream()
                .filter(m->!(m.getParty().equals(matchWaitEntity.getParty().getPartyId())))
                .forEach(this::convertReverse);

    }
    public void convertReverse(MatchWaitEntity matchWaitEntity) {
        MatchEntity matchEntity = new MatchEntity();
        int cycle = matchWaitEntity.getCycle();
        PartyEntity party = matchWaitEntity.getParty();
        matchEntity.setMember(matchWaitEntity.getMember());
        List<StadiumEntity> stdList = stadiumRepository.findAll();
        StadiumEntity stadium = NearStadium.findNearestStadiums(stdList,party.getLocationX(),party.getLocationY(),cycle+1);
        matchEntity.setStadium(stadium);
        matchEntity.setParty(party);
        matchEntity.setSearchStart(matchWaitEntity.getSearchStart());
        matchEntity.setSearchEnd(null);
        matchEntity.setCrd(matchWaitEntity.getCrd());
        matchEntity.setPoint(matchWaitEntity.getPoint());
        matchEntity.setCycle(cycle+1);
        matchRepository.save(matchEntity);
        matchWaitRepository.delete(matchWaitEntity);
    }//매치웨이트 테이블에서 다시 매치테이블로 튕겨 나오는 메소드
    public MatchWaitDTO getMatchWait(Long userId){
        MemberEntity member = memberRepository.findByUserId(userId);
        MatchWaitEntity matchWait = matchWaitRepository.findByMember(member);
        return modelMapper.map(matchWait, MatchWaitDTO.class);
    }
}
