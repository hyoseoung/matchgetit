package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.AccountState;
import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.util.FormatDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

//import java.sql.Date;

@Getter @Setter @ToString
public class AdminPageUserDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNum;
    private Gender gender;
    private String birthday;
    private Integer score;
    private String level;
    private String regDate;
    private String loginDate;
//    private String state;

    private AccountState accountState;
    private String banDateStart;
    private String banDateEnd;
    private String banReason;

    private static ModelMapper modelMapper = new ModelMapper();

//    public static AdminPageUserDTO of(UserEntity member) {
//        return modelMapper.map(member, AdminPageUserDTO.class);
//    }

    public static AdminPageUserDTO of(MemberEntity member) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(MemberEntity.class, AdminPageUserDTO.class)
                .addMappings(mapping -> {
                    mapping.map(MemberEntity::getUserId, AdminPageUserDTO::setId);
                    mapping.map(MemberEntity::getPn, AdminPageUserDTO::setPhoneNum);
                    mapping.map(MemberEntity::getBDay, AdminPageUserDTO::setBirthday);
                    mapping.map(MemberEntity::getRating, AdminPageUserDTO::setRating);
                    mapping.map(MemberEntity::getRegDate, AdminPageUserDTO::setFormattedRegDate);
                    mapping.map(MemberEntity::getLastConnectionDate, AdminPageUserDTO::setFormattedLoginDate);
                });
        return modelMapper.map(member, AdminPageUserDTO.class);
    }

    public void setScore(Integer score) {
        this.score = score;
//        int scoreNum = Integer.parseInt(this.score);
        int scoreNum = this.score;

        if (scoreNum > 1000) {
            this.level = "E";
        }
        else if (scoreNum > 850) {
            this.level = "D";
        }
        else if (scoreNum > 500) {
            this.level = "C";
        }
        else if (scoreNum > 200) {
            this.level = "B";
        }
        else {
            this.level = "A";
        }

    }


    public void setRating(Long rating) {
        int scoreNum;
        if (rating > Integer.MAX_VALUE) scoreNum = Integer.MAX_VALUE;
        else if (rating < Integer.MIN_VALUE) scoreNum = Integer.MIN_VALUE;
        else scoreNum = Math.toIntExact(rating);
        setScore(scoreNum);
    }

    public void setFormattedRegDate(Date regDate) {
        this.regDate = FormatDate.formatDateToString(regDate);
    }

    public void setFormattedLoginDate(Date loginDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.loginDate = formatter.format(loginDate);
    }

}
