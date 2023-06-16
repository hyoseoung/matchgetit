package com.matchgetit.backend.entity;

import com.matchgetit.backend.constant.AccountState;
import com.matchgetit.backend.dto.AdminPageUserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "\"SITE_USER\"")
@Getter @Setter @ToString
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true, name = "PN")
    private String phoneNum;

    @Column(name = "PW")
    private String password;

    private String gender;

    @Column(name = "b_day")
    private Date birthday;

    @Column(name = "rating")
    private String score;

    private Date regDate;
    private Date loginDate;

    @Enumerated(EnumType.STRING)
    private AccountState accountState;
//    private String state;
    private String banReason;


    public void updateUser(AdminPageUserDTO userDto) {
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.phoneNum = userDto.getPhoneNum();
        this.gender = userDto.getGender();
        this.birthday = Date.valueOf(userDto.getBirthday());
        this.score = userDto.getScore();
    }

}
