package com.matchgetit.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matchgetit.backend.constant.*;
import java.util.Date;
import java.util.List;

import com.matchgetit.backend.entity.ManagerEntity;
import com.matchgetit.backend.entity.ManagerSupportRecordEntity;
import com.matchgetit.backend.entity.PaymentRecordEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberDTO {
        private Long userId;
        @JsonIgnore
        @JsonIgnoreProperties
        private PartyDTO party;
        private String bankId;
        private String pn;
        private String email;
        private String pw;
        private String name;
        private String nameEdit;
        private Date bDay;
        private Gender gender;
        private Proficiency prfcn;
        private AccountType accountType;
        private Date regDate;
        private LogInType loginType;
        private AccountState accountState; //로그인 상태 변경
        private Long chargeId;
        private Long ownedCrd;
        private Long ownedPoint;
        private Long win;
        private Long lose;
        private Long rating;
        private Date banDateStart;
        private Date banDateEnd;
        private String banReason;
        private String recommendCount;
        private Date lastConnectionDate;
        private String managerSupportStatus; //매니저 지원의 현재 상태를 표시 미지원 "NORMAL",  지원"WAITING"
        private PayState payState;

        private ManagerDTO managerDTO; // 매니저
        private List<PaymentRecordEntity> paymentRecordEntityList; // 수정된 부분
        private List<PaymentRecordDTO> paymentRecordDTO;


        private ManagerSupportRecordDTO managerSupportRecordDTO;
        private ManagerSupportRecordEntity managerSupportRecordEntity;
        private ManagerEntity managerEntity;



}
