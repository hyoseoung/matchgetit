package com.matchgetit.backend.controller;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyDTO;
import com.matchgetit.backend.request.MatchRequest;
import com.matchgetit.backend.request.MemberIdRequest;
import com.matchgetit.backend.service.MemberService;
import com.matchgetit.backend.service.PartyAcceptService;
import com.matchgetit.backend.service.PartyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matchGetIt/match")
@AllArgsConstructor
public class MatchController {
    private final MemberService memberService;
    private final PartyService partyService;
    private final PartyAcceptService partyAcceptService;

    @PostMapping("/start")
    public ResponseEntity<String> startMatching(@RequestBody MatchRequest requestData) {
        try {
            String partyLeaderId = requestData.getPartyLeader();
            String x = requestData.getX();
            String y = requestData.getY();
            String selectedDate = requestData.getSelectedDate();
            String selectedTime = requestData.getSelectedTime();
            List<MemberIdRequest> party = requestData.getParty();
            List<MemberDTO> partyFormat = new ArrayList<>();
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            System.out.println("날짜: " + selectedDate);
            System.out.println("시간: " + selectedTime);

            // 파티 리더 추가
            MemberDTO partyLeader = memberService.findMemberById(Integer.parseInt(partyLeaderId));
            partyFormat.add(partyLeader);

            // 파티원 추가
            for (MemberIdRequest memberData : party) {
                String memberId = memberData.getId();
                MemberDTO memberDTO = memberService.findMemberById(Integer.parseInt(memberId));
                System.out.println("파티원 ID: " + memberId);
                System.out.println("파티원 이름: " + memberDTO.getName());
                partyFormat.add(memberDTO);
            }
            partyAcceptService.deletePartyAcceptByPartyLeaderId(Integer.parseInt(partyLeaderId));
            //해당 파티 Accept 엔티티 일괄 제거
            // 파티 생성 및 저장
            PartyDTO partyDTO = partyService.createParty(Integer.parseInt(partyLeaderId));
            if (partyDTO != null) {
                // 파티원들의 파티 정보 업데이트
                for (MemberDTO memberDTO : partyFormat) {
                    memberService.updateParty(memberDTO.getUserId(), partyDTO);
                }
                return new ResponseEntity<>("성공", HttpStatus.OK);
            } else {
                System.out.println("실패");
                return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getMember")
    public ResponseEntity<List<MemberDTO>> getPartyMembers(@RequestParam String id) {
        try {
            List<MemberDTO> members = partyService.getPartyMembers(Integer.parseInt(id));
            members.forEach(m-> System.out.println("파티원:"+m.getName()));
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


