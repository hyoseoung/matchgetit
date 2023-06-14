package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.MatchType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "manager")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long managerId; // (PK) 매니저ID

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; // 회원 ID

    @Column
    private Long stadiumId; // 구장ID

    @Column
    private Long partyId; // 파티ID

    @Column
    private LocalDateTime startTime; // 매칭 시작 시간

    @Column
    private String team; // 소속 팀

    @Column
    @Enumerated(EnumType.STRING)
    private MatchType matchType; // 매치 타입

    private String managerImage; //매니저 이미지

    @Column
    private LocalDateTime registrationDate; //매니저 등록 날짜

    @Column
    private String employmentStatus; //근무 상태값

    @Column
    private LocalDateTime leaveStartDate; //휴직 시작일

    @Column
    private LocalDateTime leaveEndDate; //휴직 종료일

    @Column
    private String leaveReason; //휴직 사유

//    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
//    private List<GameEntity> assignedGames; //배정된 경기

    // 생성자, getter, setter, toString 등 필요한 메소드 추가
}
