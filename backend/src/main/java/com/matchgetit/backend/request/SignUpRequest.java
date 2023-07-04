package com.matchgetit.backend.request;

import com.matchgetit.backend.constant.Gender;
import com.matchgetit.backend.constant.Proficiency;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "비밀번호는 영어 소문자, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.")
    private String password;
    private String name;
    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호는 000-0000-0000 형식으로 입력해주세요.")
    private String pn;
    private Gender gender;
    private Proficiency proficiency;
    private String birthDay;
}
