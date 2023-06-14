package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.MatchType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId; // (PK) 매니저ID

    @Column(unique = true)
    private Long memberId; // (FK, U) 회원ID

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

    // 생성자, getter, setter, toString 등 필요한 메소드 추가
}
