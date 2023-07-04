package com.matchgetit.backend.dto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matchgetit.backend.constant.GameType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDTO {
    private Long partyId;
    private MemberDTO partyLeader;
    private Long count;
    private Date applicationDate;
    private String applicationTime;
    private String address;
    private String locationX;
    private String locationY;
    private Date partyApplicationDate;
    private Long partyRatingAvg;
    private GameType gameType;
    @JsonIgnore
    @JsonIgnoreProperties
    private List<MemberDTO> members;
}

