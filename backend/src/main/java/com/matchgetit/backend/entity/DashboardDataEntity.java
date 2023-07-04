package com.matchgetit.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DASHBOARD_DATA")
public class DashboardDataEntity {
    @Id
    @Column(name = "dashboard_data_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // (오늘) 탈퇴한 회원 수
    private Integer canceledMembership;

    // (오늘) 취소된 경기 수
    private Integer canceledMatch;

}
