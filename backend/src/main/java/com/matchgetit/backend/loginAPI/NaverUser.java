package com.matchgetit.backend.loginAPI;

import com.matchgetit.backend.constant.Proficiency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverUser {
    private String id;
    private String email;
    private String name;
    private String mobile;
    private String birthyear;
    private String birthday;
    private String gender;
    private String mobile_e164;
    private Proficiency proficiency;
}
