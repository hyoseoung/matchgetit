package com.matchgetit.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matchgetit.backend.constant.*;
import com.matchgetit.backend.dto.AdminPageUserDTO;
import com.matchgetit.backend.dto.ManagerDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

//@Data
@Entity
@Table(name = "member")
@Getter
@Setter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long userId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "PARTY_ID", nullable = true)
    private PartyEntity party;

    @Column(name = "BANK_ID")
    private String bankId;

    @Column(name = "PN")
    private String pn;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PW")
    private String pw;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NAMEEDIT")
    private String nameEdit;

    @Column(name = "B_DAY")
    @Temporal(TemporalType.DATE)
    private Date bDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRFCN")
    private Proficiency prfcn;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE")
    private AccountType accountType;

    @Column(name = "REG_DATE")
    @Temporal(TemporalType.DATE)
    private Date regDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "LOGIN_TYPE")
    private LogInType loginType;

    @Column(name = "ACCOUNT_STATE")
    @Enumerated(EnumType.STRING) //로그인 상태 변경
    private AccountState accountState;

    @Column(name = "CHARGE_ID")
    private Long chargeId;

    @Column(name = "OWNED_CRD")
    private Long ownedCrd;
    @Column(name = "OWNED_POINT")
    private Long ownedPoint;

    @Column(name = "WIN")
    private Long win;
    @Column(name = "LOSE")
    private Long lose;

    @Column(name = "RATING")
    private Long rating;

    @Column(name = "BAN_DATE_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date banDateStart;

    @Column(name = "BAN_DATE_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date banDateEnd;

    @Column(name = "BAN_REASON")
    private String banReason;

    @Column(name = "RECOMMEND_COUNT")
    private String recommendCount;

    @Column(name = "LAST_CONNECTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastConnectionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "MANAGERSUPPORTSTATUS")
    private ManagerSupportStatus managerSupportStatus; //매니저 지원의 현재 상태를 표시 미지원 "0", 지원 "1"

    @Enumerated(EnumType.STRING)
    @Column(name = "PAY_STATE")
    private PayState payState;

    @OneToOne(mappedBy = "user")
    public ManagerEntity managerEntity; // 매니저

    @OneToOne(mappedBy = "managerUser")
    private ManagerSupportRecordEntity managerSupportRecordEntity;

    @OneToMany(mappedBy = "member")
    private List<PaymentRecordEntity> paymentRecordEntityList;

    @Column(name = "RANK")
    private String rank;


    public void updateProficiencyBasedOnRating() {
        if (rating >= 0 && rating <= 400) {
            prfcn = Proficiency.BEGINNER;
        } else if (rating >= 401 && rating <= 700) {
            prfcn = Proficiency.MIDDLE;
        } else if (rating >= 701 && rating <= 1100) {
            prfcn = Proficiency.ADVANCED;
        } else {
            prfcn = Proficiency.PROFESSIONAL;
        }
    }



    // 관리자 페이지에서 유저 정보 수정할 때 사용
    public void updateUser(AdminPageUserDTO userDto) {
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.pn = userDto.getPhoneNum();
        this.gender = userDto.getGender();
        this.bDay = java.sql.Date.valueOf(userDto.getBirthday());
        this.rating = Long.valueOf(userDto.getScore());
    }


    // 관리자 페이지에서 유저 계정 정지할 때 사용
    public void banUser(Date banDateStart, Date banDateEnd, String banReason) {
        this.banDateStart = banDateStart;
        this.banDateEnd = banDateEnd;
        this.banReason = banReason;
        this.accountState = AccountState.BANNED;
    }

    // 계정 정지 취소
    public void cancelBan() {
        this.banDateStart = null;
        this.banDateEnd = null;
        this.banReason = null;
        this.accountState = AccountState.ACTIVE;
    }

}
