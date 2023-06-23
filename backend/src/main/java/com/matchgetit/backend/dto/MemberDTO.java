package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.AccountType;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.LogInType;
import com.matchgetit.backend.constant.Proficiency;
import com.matchgetit.backend.entity.PartyEntity;
import lombok.*;

import java.util.Date;

@Data
@Setter
public class MemberDTO {
    private int userId;
    private String email;
    private String password;
    private PartyEntity partyEntity;
    private String name;
    private String pn;
    private Gender gender;
    private Proficiency prfcn;
    private AccountType accountType;
    private String bankId;
    private String nameEdit;
    private Date bDay;
    private String regDate;
    private LogInType logInType;
    private String accountState;
    private String chargeId;
    private String chargeBank;
    private String ownedCrd;
    private String ownedPoint;
    private String win;
    private String lose;
    private int rating;
    private Date banPeriod;
    private String recommendCount;
    private Date lastConnectionDate;
}
