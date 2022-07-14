package com.sparta.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "[a-zA-Z0-9]{3,12}$", message = "최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9) 특수문자 X")
    @Size(min = 3, message = "아이디를 3~12자로 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "[a-zA-Z0-9]{3,12}$", message = "비밀번호를 4~12자로 입력해주세요")
    @Size(min = 4, message = "비밀번호를 4~12자로 입력해주세요")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    private String checkPwd;
}
