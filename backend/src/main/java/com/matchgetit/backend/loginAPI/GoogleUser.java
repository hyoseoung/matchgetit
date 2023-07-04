package com.matchgetit.backend.loginAPI;

import com.matchgetit.backend.constant.Proficiency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GoogleUser {
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
