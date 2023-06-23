package com.matchgetit.backend.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "비밀번호는 영어 소문자, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.")
    private String password;

}