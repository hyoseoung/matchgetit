package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.MatchType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter @Setter
public class ManagerDTO {
    private Long managerId; // (PK) 매니저ID
    private Long memberId; // (FK, U) 회원ID
    private Long stadiumId; // 구장ID
    private Long partyId; // 파티ID
    private LocalDateTime startTime; // 매칭 시작 시간
    private String team; // 소속 팀
    private MatchType matchType; // 매치 타입

    // 생성자, getter, setter, toString 등 필요한 메소드 추가
}