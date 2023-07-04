package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.AcceptType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="MATCH")
@Data
public class MatchEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MATCH_ID")
    private Long matchId;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private MemberEntity member;
    @ManyToOne
    @JoinColumn(name="PARTY_ID")
    private PartyEntity party;
    @ManyToOne
    @JoinColumn(name="STD_ID",nullable = true)
    private StadiumEntity stadium;
    @Enumerated(EnumType.STRING)
    @Column(name="ACCEPT")
    private AcceptType accept;//모두 수락 완료하고 matchWait로 넘어감
    @Column(name="MATCH_SEARCH_STR")
    private Date searchStart;
    @Column(name="MATCH_SEARCH_END")
    private Date searchEnd;
    @Column(name="PYM_CRD")
    private Long crd;
    @Column(name="PYM_POINT")
    private Long point;
    @Column(name="CYCLE")
    private int cycle;
}
