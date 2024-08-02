package com.demo.mono_prac.api.service.impl;

import org.springframework.stereotype.Service;

import com.demo.mono_prac.api.request.UserJoinReq;
import com.demo.mono_prac.api.request.UserUpdateNicknameReq;
import com.demo.mono_prac.api.service.UserService;
import com.demo.mono_prac.common.execption.UserAlreadyExistException;
import com.demo.mono_prac.common.execption.UserNotFoundException;
import com.demo.mono_prac.db.entity.Users;
import com.demo.mono_prac.db.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserJpaService implements UserService {

    private final UserRepository userRepository;

    @Override
    public Users createUser(UserJoinReq userJoinReq) {
        String userId = userJoinReq.getUserId();
        if(userRepository.existsByUserId(userId)){
            throw new UserAlreadyExistException();
        }
        Users user = new Users();
        user.setUserId(userJoinReq.getUserId());
        user.setPassword(userJoinReq.getPassword());
        user.setNickname(userJoinReq.getNickname());
        return userRepository.save(user);
    }

    @Override
    public Users getUserByUserId(String userId) {
        Users user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    @Override
    public Users getUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    @Override
    public void removeUser(String userId) {
        Users user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(user);
    }

    @Override
    public void modifyUserNickname(UserUpdateNicknameReq userDTO) {
        Users user = userRepository.findByUserId(userDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException());
        user.setNickname(userDTO.getNickname());
    }

}
