//package com.matchgetit.backend.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name="MATCH_SCO")
//public class MatchScoreEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="MATCH_SCO_ID")
//    private Long matchScoId;
//    @OneToOne(mappedBy = "MATCH_SCO_ID")
//    private MatchRecEntity matchRec;
//    @Column(name="F_SCORE")
//    private String firstScore;
//    @Column(name="S_SCORE")
//    private String secondScore;
//    @Column(name="T_SCORE")
//    private String ThridScore;
//}
