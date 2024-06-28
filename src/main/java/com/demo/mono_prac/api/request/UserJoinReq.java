package com.demo.mono_prac.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinReq {
    @Email
    String userId;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{12,}$", message = "대문자,소문자,숫자,특수문자가 들어간 길이가 12글자 이상이다.")
    String password;
    String nickname;
}
