package com.matchgetit.backend.service;

import com.matchgetit.backend.constant.AcceptType;
import com.matchgetit.backend.constant.GameType;
import com.matchgetit.backend.dto.MatchDTO;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.entity.*;
import com.matchgetit.backend.repository.*;
import com.matchgetit.backend.util.FormatDate;
import com.matchgetit.backend.util.NearStadium;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchService{
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final StadiumRepository stadiumRepository;
    private final MatchRepository matchRepository;
    private final PartyRepository partyRepository;
    private final MatchWaitRepository matchWaitRepository;
    @Scheduled(fixedDelay=5*1000)//메인 로직 batch 대용
    public void match() {
        List<MatchEntity> entityList = matchRepository.findAllByOrderByCycleDesc();
        List<MatchDTO> list = entityList.stream().map(m -> modelMapper.map(m, MatchDTO.class)).toList();
        if(entityList.size()>=2){//인원 넘으면 돌리기
            Map<Long, List<MatchDTO>> fMatchMap = list.stream().collect(Collectors.groupingBy(m -> m.getStadium().getStdId(), Collectors.toList()));
            //스타디움 별로 나누기
            for (Map.Entry<Long, List<MatchDTO>> fMatchEntry : fMatchMap.entrySet()) {
                List<MatchDTO> filteredByStdId = fMatchEntry.getValue();
                Map<String, List<MatchDTO>> sMatchMap = filteredByStdId.stream()
                        .collect(Collectors
                                .groupingBy(m -> FormatDate
                                        .formatDateToString(m.getParty().getApplicationDate())+" "+m.getParty()
                                        .getApplicationTime(), Collectors.toList()));
                for (Map.Entry<String, List<MatchDTO>> sMatchEntry : sMatchMap.entrySet()){
                    List<MatchDTO> filteredByDate = sMatchEntry.getValue();
                    Map<GameType, List<MatchDTO>> tMatchMap = filteredByDate.stream().collect(Collectors.groupingBy(m->m.getParty().getGameType(),Collectors.toList()));
                    for(Map.Entry<GameType,List<MatchDTO>> tMatchEntry: tMatchMap.entrySet()){
                        List<MatchDTO> filteredByGameType=tMatchEntry.getValue();
                        Map<Long,List<MatchDTO>> foMatchMap = filteredByGameType.stream().collect(Collectors.groupingBy(m->m.getParty().getCount(),Collectors.toList()));
                        for (Map.Entry<Long, List<MatchDTO>> foMatchEntry : foMatchMap.entrySet()) {
                            List<MatchDTO> filteredByPersons =foMatchEntry.getValue();
                            if(tMatchEntry.getKey().equals(GameType.FRIENDLY)){
                                System.out.println("친선 :"+(matchPersons(filteredByPersons)?"12명 매칭 성공":"거절되거나 인원 없음"));
                            }else if(tMatchEntry.getKey().equals(GameType.LEAGUE)) {
                                Map<Long,List<MatchDTO>> filteredByRating = filteredByPersons.stream().collect(Collectors.groupingBy(m->m.getParty().getPartyRatingAvg()/100L));
                                for(Map.Entry<Long,List<MatchDTO>> fifEntry:filteredByRating.entrySet()){
                                    List<MatchDTO> filteredRatingList = fifEntry.getValue();
                                    System.out.println("리그 :"+(matchPersons(filteredRatingList)?"12명 매칭 성공":"거절되거나 인원 없음"));
                                }
                            }else{
                                System.out.println("올바르지 않은 게임 타입이 발견되었습니다 DB에서 엔티티 제거 요망:"+tMatchEntry.getKey());
                            }
                        }
                    }
                }
            }//엔트리로 나눠서 나머지 연산(한꺼번에 스트링으로 묶어서 나눠서 연산하는 게 나을까 아니면.. 나눠서 루프 횟수 추가하는 게 나을까..)
        }
    }
    private boolean matchPersons(List<MatchDTO> filteredByPersons){
        filteredByPersons.forEach( m-> System.out.println( m.getParty().getGameType().equals(GameType.FRIENDLY)?"친선":"리그"+" "+"들어온 회원:"+m.getMember().getName()));
        System.out.println(filteredByPersons.size());
        if(filteredByPersons.size()>=2){
            List<MatchDTO> scatteredList = filteredByPersons.stream().limit(2).toList();
            //같은 부류로 나눈 다음 12명 처리
            scatteredList.stream().filter(m->m.getAccept().equals(AcceptType.WAIT)).forEach(e->updateAccept(e.getMember().getUserId(),AcceptType.MATCHING));
            long agreementFlag = scatteredList.stream().filter(m->m.getAccept().equals(AcceptType.AGREE)).count();
            long disagreementFlag = scatteredList.stream().filter(m->m.getAccept().equals(AcceptType.DISAGREE)).count();
            if(agreementFlag==scatteredList.size()){
                //매치 >> 매치웨이트 처리
                for( int i=0; i<scatteredList.size();i++)convert(modelMapper.map(scatteredList.get(i),MatchEntity.class),i<scatteredList.size()/2?"A":"B");
                return true;
            }else if(disagreementFlag>=1){
                scatteredList.forEach(m->updateAccept(m.getMember().getUserId(),AcceptType.WAIT));
                return false;
            }
        }
        return false;
    }//매칭 관련 로직

    public void createMatch(MemberDTO member){//처음 신청 전용 매치 메소드
        MemberEntity memberEntity = memberRepository.findByUserId(member.getUserId());
        MatchEntity match = new MatchEntity();
        PartyEntity party = memberEntity.getParty();
        match.setMember(modelMapper.map(member, MemberEntity.class));
        match.setParty(party);
        match.setAccept(AcceptType.WAIT);
        match.setCycle(1);
        List<StadiumEntity> stadiumEntityList= stadiumRepository.findAll();
        if(stadiumEntityList.size()!=0){
            StadiumEntity stadium = NearStadium.findNearestStadiums(stadiumEntityList,party.getLocationX(), party.getLocationY(), match.getCycle());
            match.setStadium(stadium);
        }else{
            throw new RuntimeException("스타디움 없음");
        }
//        if(memberEntity.getCreditState().equals(CreditState.CREDIT)){
//            memberEntity.setOwnedCrd(memberEntity.getOwnedCrd()-2000);
//        }else if(memberEntity.getCreditState().equals(CreditState.POINT)){
//            memberEntity.setOwnedPoint(memberEntity.getOwnedPoint()-2000);
//        }else{
//            throw new RuntimeException("잘못된 상태 값: "+memberEntity.getCreditState());
//        } //여기 로직에서 실제 포인트 차감
        //장소의 해당 시간에 MATCH_WAIT에 있다면 오류 처리
        match.setSearchStart(new Date());
        matchRepository.save(match);
    }
    public List<MatchDTO> findAllMatch(){
        List<MatchEntity> list = matchRepository.findAll();
        return list.stream().map(m->modelMapper.map(m,MatchDTO.class)).toList();
    }
    public void updateAccept(Long userId,AcceptType accept){
        MemberEntity member= memberRepository.findByUserId(userId);
        MatchEntity match= matchRepository.findByMember(member);
        match.setAccept(accept);
        matchRepository.save(match);
    }
    public List<MatchDTO> getMatchList(Long userId) {
        MemberEntity member = memberRepository.findByUserId(userId);
        MatchEntity match = matchRepository.findByMember(member);
        if (match == null) {
            return null; // 매치가 없는 경우 null 반환 또는 빈 리스트로 변경할 수 있습니다.
        }
        StadiumEntity stadium = match.getStadium();
        PartyEntity party = match.getParty();

        System.out.println(party.getApplicationDate());
        System.out.println(stadium.getStdName());
        System.out.println(party.getApplicationTime());

        List<MatchEntity> agreeEntityList = matchRepository.findByStadiumAndParty_ApplicationTimeAndParty_ApplicationDate_AndParty_GameType(
                stadium, party.getApplicationTime(), party.getApplicationDate(), party.getGameType());
        if(party.getGameType().equals("LEAGUE"))agreeEntityList= agreeEntityList.stream().filter(m->m.getParty().getPartyRatingAvg()/100 == party.getPartyRatingAvg()/100).toList();
        //DB에서 꺼낼 시에 String값으로 비교 가능(리그면 점수대로 걸러냄)

        //리그면 점수대 별로 나눔
        List<MatchDTO> matchList = agreeEntityList.stream()
                .map(m -> modelMapper.map(m, MatchDTO.class))
                .toList();

        return matchList.size()>=2?matchList:null;
    }
    @Transactional
    public void deleteMatch(Long partyId){
        PartyEntity party = partyRepository.findByPartyId(partyId);
        if (party == null) {
            throw new IllegalArgumentException("해당 파티가 존재하지 않습니다.");
        }
        matchRepository.deleteMatchEntitiesByParty(party);
        System.out.println("삭제 성공");
    }
    public void updateAccept(MemberDTO member, AcceptType accept){
        MatchEntity match = matchRepository.findByMember(modelMapper.map(member,MemberEntity.class));
        match.setAccept(accept);
        matchRepository.save(match);
    }
    public void convert(MatchEntity matchEntity, String team) {
        MatchWaitEntity matchWaitEntity = new MatchWaitEntity();
        matchWaitEntity.setMember(matchEntity.getMember());
        matchWaitEntity.setStadium(matchEntity.getStadium());
        matchWaitEntity.setParty(matchEntity.getParty());
        matchWaitEntity.setSearchStart(matchEntity.getSearchStart());
        matchWaitEntity.setSearchEnd(new Date());
        matchWaitEntity.setCrd(matchEntity.getCrd());
        matchWaitEntity.setPoint(matchEntity.getPoint());
        matchWaitEntity.setCycle(matchEntity.getCycle());
        matchWaitEntity.setTeam(team);
        matchRepository.delete(matchEntity);
        matchWaitRepository.save(matchWaitEntity);
    }
}
