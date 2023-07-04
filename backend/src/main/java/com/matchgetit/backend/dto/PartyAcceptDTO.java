package com.matchgetit.backend.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.matchgetit.backend.constant.AcceptType;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyAcceptDTO {
        private Long partyAcceptId;
        private MemberDTO partyLeader;
        private AcceptType agreement;
        private Date overTime;
        private MemberDTO user;
}
