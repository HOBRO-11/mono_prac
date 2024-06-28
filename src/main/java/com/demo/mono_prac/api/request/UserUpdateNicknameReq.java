package com.demo.mono_prac.api.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateNicknameReq {
    @Email
    private String userId;
    private String nickname;
}
