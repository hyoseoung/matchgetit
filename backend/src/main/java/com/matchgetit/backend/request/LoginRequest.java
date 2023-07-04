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
    @Pattern(regexp = "^(?=.[A-Za-z])(?=.\\d)(?=.[@$!%?&#^_])[\\w@$!%*?&#^]{8,}$",
            message = "비밀번호 규칙이 \n 올바르지 \n 않습니다")
    private String password;

}