package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.EmploymentStatus;
import com.matchgetit.backend.constant.MatchType;
import com.matchgetit.backend.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter @Setter
public class ManagerDTO {
    private Long managerId; // (PK) 매니저ID
    private MemberEntity user; // 회원 ID
    private Long stadiumId; // 구장ID
    private Long partyId; // 파티ID
    private LocalDateTime startTime; // 매칭 시작 시간
    private String team; // 소속 팀
    private MatchType matchType; // 매치 타입
    private String managerImage; // 매니저 이미지
    private LocalDateTime registrationDate; // 매니저 등록 날짜
    private EmploymentStatus employmentStatus; // 근무 상태값
    private LocalDate leaveStartDate; // 휴직 시작일
    private LocalDate leaveEndDate; // 휴직 종료일
    private String leaveReason; // 휴직 사유

//    private List<GameDTO> assignedGames; // 배정된 경기

}