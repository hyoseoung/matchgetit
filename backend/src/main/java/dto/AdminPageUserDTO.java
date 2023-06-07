package com.project.matchgetit.admin;

import com.project.matchgetit.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

//import java.sql.Date;

@Getter @Setter @ToString
public class AdminPageUserDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNum;
    private String gender;
    private String birthday;
    private String score;
    private String rating;
    private String regDate;
    private String loginDate;
    private String state;

    private static ModelMapper modelMapper = new ModelMapper();

    public static AdminPageUserDTO of(User user) {
        return modelMapper.map(user, AdminPageUserDTO.class);
    }

    public void setScore(String score) {
        this.score = score;
        int scoreNum = Integer.parseInt(this.score);

        if (scoreNum > 1000) {
            this.rating = "E";
        }
        else if (scoreNum > 850) {
            this.rating = "D";
        }
        else if (scoreNum > 500) {
            this.rating = "C";
        }
        else if (scoreNum > 200) {
            this.rating = "B";
        }
        else {
            this.rating = "A";
        }

    }
}
