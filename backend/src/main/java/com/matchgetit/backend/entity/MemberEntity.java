package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.constant.Proficiency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "member")
@Data
@Setter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "PARTY_ID", nullable=true)
    private PartyEntity partyEntity;

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
    private String accountState;

    @Column(name = "CHARGE_ID")
    private String chargeId;

    @Column(name = "CHARGE_BANK")
    private String chargeBank;

    @Column(name = "OWNED_CRD")
    private String ownedCrd;

    @Column(name = "OWNED_POINT")
    private int ownedPoint;

    @Column(name = "WIN")
    private String win;

    @Column(name = "LOSE")
    private String lose;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "BAN_PERIOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date banPeriod;

    @Column(name = "RECOMMEND_COUNT")
    private String recommendCount;

    @Column(name = "LAST_CONNECTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastConnectionDate;

}

