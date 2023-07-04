//package com.matchgetit.backend.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Date;
//
//@Entity
//@Table(name="MATCH_REC")
//@Getter
//@Setter
//public class MatchRecEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="MATCH_REC_ID")
//    private Long matchRecId;
//    @Column(name="PARTY_LEADER_ID")
//    private Long partyLeaderId;
//    @ManyToOne
//    @JoinColumn(name="STD_ID",nullable=true)
//    private StadiumEntity stadium;
//    //matchWait 칼럼 외래키
//    @OneToOne(mappedBy = "MATCH_SCO_ID",nullable=true)
//    private MatchScoreEntity matchScore;
//    @Column(name="MATCH_SEARCH_STR")
//    private Date matchSearchStr;
//    @Column(name="TEAM")
//    private String team;
//    @Column(name="MATCH_STATE")
//    private String matchState;
//}
