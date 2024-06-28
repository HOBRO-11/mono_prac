package com.demo.mono_prac.api.service;

import com.demo.mono_prac.api.request.UserJoinReq;
import com.demo.mono_prac.api.request.UserUpdateNicknameReq;
import com.demo.mono_prac.db.entity.Users;

public interface UserService {
    Users createUser(UserJoinReq userJoinReq);
    Users getUserByUserId(String userId);
    Users getUserById(Long id);
    void removeUser(String userId);
    void modifyUserNickname(UserUpdateNicknameReq userDTO);
}
