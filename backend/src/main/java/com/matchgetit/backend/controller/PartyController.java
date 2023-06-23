package com.matchgetit.backend.controller;
import com.matchgetit.backend.constant.AcceptType;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyAcceptDTO;
import com.matchgetit.backend.service.MemberService;
import com.matchgetit.backend.service.PartyAcceptService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matchGetIt/party")
@AllArgsConstructor
public class PartyController {
    private final MemberService memberService;
    private final PartyAcceptService partyAcceptService;

    @PostMapping("/searchId")
    public ResponseEntity<PartyAcceptDTO> findAccount(@RequestParam String id,@RequestParam String partyLeaderId) {
        try {
            MemberDTO member = memberService.findMemberById(Integer.parseInt(id));
            //PartyAcceptTable 할당
            if(member!=null){
                partyAcceptService.createPartyAccept(Integer.parseInt(partyLeaderId),Integer.parseInt(id));
                PartyAcceptDTO partyAcceptDTO = partyAcceptService.getPartyAcceptByUserIdAndPartyLeaderId(Integer.parseInt(id),Integer.parseInt(partyLeaderId));

                return new ResponseEntity<>(partyAcceptDTO, HttpStatus.OK);//리턴은 PartyAcceptDTO로 해야함
            }else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }//회원이 존재하지 않는 경우
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); //형식이 맞지 않는 경우
        }
    }
    @PostMapping("/renewInviteData")//회원 초대 조회 메소드
    public ResponseEntity<List<PartyAcceptDTO>> renewData(@RequestParam String id) {
        try {
            List<PartyAcceptDTO> partyAccepts = partyAcceptService.getPartyAccept(Integer.parseInt(id));//리스트 갖고옴
            if (partyAccepts.isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            } else {
                partyAccepts.forEach(m-> System.out.println("파티 초대:"+m.getPartyAcceptId()));
                return new ResponseEntity<>(partyAccepts, HttpStatus.OK);
            }
        } catch (Exception e) {
            // 예외 처리
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/renewPartyAcceptData")//파티멤버 수락 여부 확인 메소드
    public ResponseEntity<List<PartyAcceptDTO>> renewAcceptData(@RequestParam String partyLeaderId){
        try {
            List<PartyAcceptDTO> partyAccepts = partyAcceptService.getPartyAcceptByPartyLeaderId(Integer.parseInt(partyLeaderId));
            partyAccepts.forEach(m-> System.out.println(m.getPartyAcceptId()));
            return new ResponseEntity<>(partyAccepts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/accept/{partyAcceptId}")
    public ResponseEntity<String> acceptParty(@PathVariable int partyAcceptId) {
        try {
            partyAcceptService.updatePartyAcceptStatus(partyAcceptId, AcceptType.AGREE);
            return new ResponseEntity<>("성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reject/{partyAcceptId}")
    public ResponseEntity<String> rejectParty(@PathVariable int partyAcceptId) {
        try {
            partyAcceptService.updatePartyAcceptStatus(partyAcceptId,AcceptType.DISAGREE);
            return new ResponseEntity<>("성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
