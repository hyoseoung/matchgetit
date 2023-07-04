package com.matchgetit.backend.dto;

import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyEntity;
import com.matchgetit.backend.entity.StadiumEntity;
import lombok.Data;

import java.util.Date;
@Data
public class MatchWaitDTO {
    private Long matchWaitId;
    private MemberDTO member;
    private StadiumDTO stadium;
    private PartyDTO party;
    private Date searchStart;
    private Date searchEnd;
    private String team;
    private Long crd;
    private Long point;
    private int cycle;
}
