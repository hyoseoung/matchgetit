package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.AcceptType;
import com.matchgetit.backend.dto.MemberDTO;
import com.matchgetit.backend.dto.PartyDTO;
import com.matchgetit.backend.dto.StadiumDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.PartyEntity;
import com.matchgetit.backend.entity.StadiumEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
public class MatchDTO{
    private Long matchId;
    private MemberDTO member;
    private PartyDTO party;
    private StadiumDTO stadium;
    private AcceptType accept;
    private Date searchStart;
    private Date searchEnd;
    private Long crd;
    private Long point;
    private int cycle;
}
