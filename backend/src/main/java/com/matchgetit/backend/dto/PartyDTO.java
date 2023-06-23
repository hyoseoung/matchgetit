package com.matchgetit.backend.dto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Setter
public class PartyDTO {
    private int partyId;
    private int partyLeader;
    private Date applicationDate;
    private String applicationTime;
    private String locationX;
    private String locationY;
    private int stadiumId;
    private List<MemberDTO> partyMembers;
}

