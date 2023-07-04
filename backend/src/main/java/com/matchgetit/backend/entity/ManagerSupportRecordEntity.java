package com.matchgetit.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ManagerSupportRecords")
@Getter
@Setter
public class ManagerSupportRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id; // (PK) 매니저 지원 양식 기록의 고유 식별자

    @OneToOne
    @JoinColumn(name = "user_id")
    private MemberEntity managerUser;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;

    @Column(name = "submission_date")
    private Date submissionDate; // 매니저 지원 양식 제출일

    @Column(name = "activity_zone")
    private String activityZone; // 활동 권역
}
