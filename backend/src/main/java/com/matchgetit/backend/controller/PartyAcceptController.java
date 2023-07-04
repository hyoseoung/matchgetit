package com.matchgetit.backend.controller;
import com.matchgetit.backend.config.JwtTokenProvider;
import com.matchgetit.backend.constant.AcceptType;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyAcceptDTO;
import com.matchgetit.backend.service.MemberService;
import com.matchgetit.backend.service.PartyAcceptService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matchGetIt/partyAccept")
@AllArgsConstructor
public class PartyAcceptController {
    private final MemberService memberService;
    private final PartyAcceptService partyAcceptService;

    @PostMapping("/searchId")
    public ResponseEntity<PartyAcceptDTO> findAccount(@RequestParam String id, @RequestParam String partyLeaderId) {
        try {
            System.out.println("보내는 id:"+Long.parseLong(partyLeaderId));
            System.out.println("받는 id:"+Long.parseLong(id));
            MemberDTO member = memberService.findMemberById(Long.parseLong(id));
            //PartyAcceptTable 할당
            if(member!=null){
                partyAcceptService.createPartyAccept(Long.parseLong(partyLeaderId),Long.parseLong(id));
                PartyAcceptDTO partyAcceptDTO = partyAcceptService.getPartyAcceptByUserIdAndPartyLeaderId(Long.parseLong(id),Long.parseLong(partyLeaderId));

                return new ResponseEntity<>(partyAcceptDTO, HttpStatus.OK);//리턴은 PartyAcceptDTO로 해야함
            }else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }//회원이 존재하지 않는 경우
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); //형식이 맞지 않는 경우
        }
    }
    @PostMapping("/renewInviteData")//회원 초대 조회 메소드
    public ResponseEntity<List<PartyAcceptDTO>> renewData(@RequestParam String id) {
        try {
            List<PartyAcceptDTO> partyAccepts = partyAcceptService.getPartyAccept(Long.parseLong(id));//리스트 갖고옴
            return new ResponseEntity<>(partyAccepts, HttpStatus.OK);
        } catch (Exception e) {
            // 예외 처리
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/renewPartyAcceptData")//파티멤버 수락 여부 확인 메소드
    public ResponseEntity<List<PartyAcceptDTO>> renewAcceptData(@RequestParam String partyLeaderId){
        try {
            List<PartyAcceptDTO> partyAccepts = partyAcceptService.getPartyAcceptByPartyLeaderId(Long.parseLong(partyLeaderId));
            partyAccepts.forEach(m-> System.out.println(m.getPartyAcceptId()));
            return new ResponseEntity<>(partyAccepts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/deleteInvite")//파티 제외 메소드
    public ResponseEntity<String> deleteAcceptData(@RequestParam String partyLeaderId,@RequestParam String userId){
        try {
            partyAcceptService.deletePartyAcceptByPartyLeaderIdAndPartyMemberId(Long.parseLong(partyLeaderId),Long.parseLong(userId));
            return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/accept/{partyAcceptId}")
    public ResponseEntity<String> acceptParty(@PathVariable Long partyAcceptId) {
        try {
            partyAcceptService.updatePartyAcceptStatus(partyAcceptId, AcceptType.AGREE);
            return new ResponseEntity<>("성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reject/{partyAcceptId}")
    public ResponseEntity<String> rejectParty(@PathVariable Long partyAcceptId) {
        try {
            partyAcceptService.updatePartyAcceptStatus(partyAcceptId,AcceptType.DISAGREE);
            return new ResponseEntity<>("성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
