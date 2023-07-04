package com.matchgetit.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ManagerSupportRecordDTO {
    private Long id; // (PK) 매니저 지원 양식 기록의 고유 식별자
    private MemberDTO user; // 매니저 지원 양식을 제출한 유저 정보
    private ManagerDTO manager; // 매니저 정보
    private Date submissionDate; // 매니저 지원 양식 제출일
    private String activityZone; // 활동 권역


}