package com.matchgetit.backend.controller;
import com.matchgetit.backend.constant.AcceptType;
import com.matchgetit.backend.constant.GameType;
import com.matchgetit.backend.dto.MatchDTO;
import com.matchgetit.backend.dto.MatchWaitDTO;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyDTO;
import com.matchgetit.backend.request.MatchRequest;
import com.matchgetit.backend.request.MemberIdRequest;
import com.matchgetit.backend.service.*;
import com.matchgetit.backend.util.FormatDate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/matchGetIt/match")
@AllArgsConstructor
public class MatchController {
    private final MemberService memberService;
    private final PartyService partyService;
    private final PartyAcceptService partyAcceptService;
    private final MatchService matchService;
    private final MatchWaitService matchWaitService;

    @PostMapping("/start")
    public ResponseEntity<String> startMatching( @RequestBody MatchRequest requestData) {
        try {

            String partyLeaderIdRequest = requestData.getPartyLeader();
            String x = requestData.getX();
            String y = requestData.getY();
            String address=requestData.getAddress();
            String selectedDate = requestData.getSelectedDate();
            String selectedTime = requestData.getSelectedTime();
            GameType gameType = requestData.getGameType();
            List<MemberIdRequest> party = requestData.getParty();
            List<MemberDTO> partyFormat = new ArrayList<>();
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            System.out.println("날짜: " + selectedDate);
            System.out.println("시간: " + selectedTime);
            Long partyLeaderId = Long.parseLong(partyLeaderIdRequest);
            System.out.println("partyLeaderId :"+ partyLeaderId);
            if(6%(party.size()+1)!=0){
                throw new IllegalArgumentException("1,2,3,6명으로만 신청가능");
            }
            //포인트가 충분한가?(보류: 결제 시스템 미구현)
            if(matchWaitService.validMatch(x,y,FormatDate.parseDate(selectedDate),selectedTime)){
                throw new IllegalArgumentException("신청한 위치나 시간을 변경해주십시오!(이미 잡혀있음)");
            }

            //가능 시간인가 판별하는 로직
            partyService.createParty(partyLeaderId,address,x,y, FormatDate.parseDate(selectedDate),selectedTime,gameType);
            PartyDTO partyDTO = partyService.findPartyByPartyLeaderId(partyLeaderId);

            // 파티 생성 및 저장


            // 파티원 추가
            for (MemberIdRequest memberData : party) {
                String memberId = memberData.getId();
                MemberDTO memberDTO = memberService.findMemberById(Long.parseLong(memberId));
                System.out.println("파티원 ID: " + memberId);
                System.out.println("파티원 이름: " + memberDTO.getName());
                partyFormat.add(memberDTO);
            }
            MemberDTO partyLeader = memberService.findMemberById(partyLeaderId);
            partyFormat.add(partyLeader);
            partyAcceptService.deletePartyAcceptByPartyLeaderId(partyLeaderId);
            //해당 파티 Accept 엔티티(파티초대 관련) 일괄 제거
            if (partyDTO != null) {
                // 파티원들의 파티 정보 업데이트
                partyFormat.forEach(m-> memberService.updateParty(m.getUserId(), partyDTO));
                System.out.println(">>>>>>>>>>>>>>>>>>>");
                partyFormat.forEach(m-> System.out.println(m.getName()));
                partyFormat.forEach(matchService::createMatch); //매치 테이블 추가
                PartyDTO renewPartyDTO = memberService.findMemberById(partyLeaderId).getParty();
                partyService.updatePartyCount(renewPartyDTO,(long)partyFormat.size());
                return new ResponseEntity<>("성공", HttpStatus.OK);
            } else {
                System.out.println("실패");
                return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }//403 예외처리, 결제 포인트 예외 처리, 등등 해야함
    }
    @PostMapping("/getMember")
    public ResponseEntity<List<MemberDTO>> getPartyMembers(@RequestParam String id) {
        try {
            List<MemberDTO> members = partyService.getPartyMembers(Long.parseLong(id));
            MemberDTO member= memberService.findMemberById(Long.parseLong(id));
            PartyDTO party = member.getParty();
            partyService.updatePartyRatingAvg(party);

            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/getParty")
    public ResponseEntity<PartyDTO> getPartyData(@RequestParam String id) {
        try {
            MemberDTO member= memberService.findMemberById(Long.parseLong(id));
            PartyDTO party = member.getParty();
            if(party!=null){
                System.out.println("파티 :"+member.getParty().getPartyId());
                return new ResponseEntity<>(member.getParty(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/renewMatchList")
    public ResponseEntity<List<MatchDTO>> getMatchList(@RequestParam String id){
        try{
            System.out.println("갱신 요청 회원:"+Long.parseLong(id));
            List<MatchDTO> matchAgreeList=matchService.getMatchList(Long.parseLong(id));
            return new ResponseEntity<>(Objects.requireNonNullElseGet(matchAgreeList, ArrayList::new), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //주석 처리한 수락 거절 요청 메소드 여기다가 만들어줘
    @PostMapping("/matchAccept")
    public ResponseEntity<String> acceptMatch(@RequestParam String id) {
        try {
            MemberDTO member = memberService.findMemberById(Long.parseLong(id));
            matchService.updateAccept(member, AcceptType.AGREE);

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/matchReject")
    public ResponseEntity<String> rejectMatch(@RequestParam String id) {
        try {
            MemberDTO member = memberService.findMemberById(Long.parseLong(id));
            matchService.updateAccept(member, AcceptType.DISAGREE);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping("/cancel")
    public ResponseEntity<String> cancelMatch(@RequestParam String id) {
        try {
            MemberDTO member= memberService.findMemberById(Long.parseLong(id));
            Long partyId = member.getParty().getPartyId();
            matchService.deleteMatch(partyId);
            partyService.deleteParty(partyId);
            return new ResponseEntity<>("취소 성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("취소 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//실시간 매치 모드 종료 메소드 로직(파티 해산)






    @PostMapping("/cancelMatchWait")
    public ResponseEntity<String> cancelMatchWait(@RequestParam String id) {
        try {
            MemberDTO member= memberService.findMemberById(Long.parseLong(id));
            Long partyId = member.getParty().getPartyId();
            MatchWaitDTO matchWait = matchWaitService.findMatchWaitByMemberId(Long.parseLong(id));
            matchWaitService.deleteMatchWait(matchWait);
            return new ResponseEntity<>("취소 성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("취소 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } //수락이 된 상태로 매칭 결과 페이지에서 취소 누르는 로직(속한 파티 해산+나머지 파티 다시 match테이블로 가게 함)
    @PostMapping("/getMatchWaitList")
    public ResponseEntity<List<MatchWaitDTO>> getMatchWaitList(@RequestParam String id){

        MatchWaitDTO matchWait = matchWaitService.findMatchWaitByMemberId(Long.parseLong(id));
        if(matchWait!=null){
            List<MatchWaitDTO> matchWaitList=matchWaitService.findMatchListByMemberId(Long.parseLong(id));
            matchWaitList.forEach(m-> System.out.println("매치 웨이트한 회원: >>>>>>>>>>"+m.getMember().getName()));
            return new ResponseEntity<>(matchWaitList,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        }


    }
}//매칭 결과에 있는 리스트 반환 메소드


