//package com.matchgetit.backend.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name="PARTY_REC")
//public class PartyRecEntity {//파티 기록 테이블(매치 기록 테이블과 같이 드감)
//    //파티 테이블하고 구조는 다름 한명한명 다 들어감
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="PARTY_REC_ID")
//    private Long partyRecId;
//    @ManyToOne
//    @JoinColumn(name = "PARTY_LEADER_ID")
//    private MemberEntity partyLeader;
//    @Column(name="PARTY_COUNT")
//    private Long count;
//    @Column(name = "APPLICATION_DATE")
//    private Date applicationDate;//사용자 선택 날짜
//    @Column(name = "APPLICATION_TIME")
//    private String applicationTime;//사용자 선택 시간
//    @Column(name="ADDRESS")
//    private String address;
//    @Column(name = "LOCATION_X")
//    private String locationX;
//    @Column(name = "LOCATION_Y")
//    private String locationY;
//    @Column(name="PARTY_APPLICATION_DATE")
//    private Date partyApplicationDate;//파티 결성 시간
//    @Column(name="GAME_TYPE")
//    private String gameType;
//    @ManyToOne
//    @JoinColumn(name="USER_ID")
//    private MemberEntity member;
//}
