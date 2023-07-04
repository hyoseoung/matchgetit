package com.matchgetit.backend.request;

import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.Proficiency;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class UpdateRequest {
    private String email;
    private String password;
    private String name;
    private String pn;
}