package com.matchgetit.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="MATCH_WAIT")
@Data
public class MatchWaitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MATCH_WAIT_ID")
    private Long matchWaitId;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private MemberEntity member;
    @ManyToOne
    @JoinColumn(name="STD_ID")
    private StadiumEntity stadium;
    @ManyToOne
    @JoinColumn(name="PARTY_ID")
    private PartyEntity party;
    @Column(name="MATCH_SEARCH_STR")
    private Date searchStart;
    @Column(name="MATCH_SEARCH_END")
    private Date searchEnd;
    @Column(name="TEAM")
    private String team;
    @Column(name="PYM_CRD")
    private Long crd;
    @Column(name="PYM_POINT")
    private Long point;
    @Column(name="CYCLE")
    private int cycle;
}//이 테이블 용도는 sheduled 가 붙은 메소드에 관여 받지 않는 테이블임 나머지는 매니저가 관리
