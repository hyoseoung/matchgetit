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
    private Long userId; // (FK, U) 회원ID
    private UserDTO user; // 회원 정보
    private Long stadiumId; // 구장ID
    private Long partyId; // 파티ID
    private LocalDateTime startTime; // 매칭 시작 시간
    private String team; // 소속 팀
    private MatchType matchType; // 매치 타입
    private String managerImage; // 매니저 이미지
    private LocalDateTime registrationDate; // 매니저 등록 날짜
    private String employmentStatus; // 근무 상태값
    private LocalDateTime leaveStartDate; // 휴직 시작일
    private LocalDateTime leaveEndDate; // 휴직 종료일
    private String leaveReason; // 휴직 사유

//    private List<GameDTO> assignedGames; // 배정된 경기

    // 생성자, getter, setter, toString 등 필요한 메소드 추가
}