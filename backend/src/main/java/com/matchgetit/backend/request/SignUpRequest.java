package com.matchgetit.backend.request;

import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.Proficiency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String password;
    private String name;
    private String pn;
    private Gender gender;
    private Proficiency proficiency;
}
