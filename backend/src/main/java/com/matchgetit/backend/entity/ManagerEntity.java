package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.EmploymentStatus;
import com.matchgetit.backend.constant.MatchType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "manager")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class ManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long managerId; // (PK) 매니저ID

    @OneToOne
    @JoinColumn(name = "user_id")
    private MemberEntity user; // 회원 ID

    @Column(name = "STADIUM_ID")
    private Long stadiumId; // 구장ID

    @Column(name = "PARTY_ID")
    private Long partyId; // 파티ID

    @Column(name = "START_TIME")
    private LocalDateTime startTime; // 매칭 시작 시간

    @Column(name = "TEAM")
    private String team; // 소속 팀

    @Column(name = "MATCH_TYPE")
    @Enumerated(EnumType.STRING)
    private MatchType matchType; // 매치 타입

    @Column(name = "MANAGER_IMAGE")
    private String managerImage; //매니저 이미지

    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate; //매니저 등록 날짜

    @Enumerated(EnumType.STRING)
    @Column(name = "EMPLOYMENT_STATUS")
    private EmploymentStatus employmentStatus; //근무 상태값

    @Column(name = "LEAVE_START_DATE")
    private LocalDate leaveStartDate; //휴직 시작일

    @Column(name = "LEAVE_END_DATE")
    private LocalDate leaveEndDate; //휴직 종료일

    @Column(name = "LEAVE_REASON")
    private String leaveReason; //휴직 사유

//    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
//    private List<GameEntity> assignedGames; //배정된 경기
}
