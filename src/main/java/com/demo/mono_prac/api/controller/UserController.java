package com.demo.mono_prac.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mono_prac.api.request.UserJoinReq;
import com.demo.mono_prac.api.request.UserUpdateNicknameReq;
import com.demo.mono_prac.api.response.UserResp;
import com.demo.mono_prac.api.service.UserService;
import com.demo.mono_prac.db.entity.Users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping
    @Transactional
    public ResponseEntity<String> join(@RequestBody @Valid UserJoinReq userJoinReq) {
        userService.createUser(userJoinReq);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserResp> getUserInfo(@PathVariable String userId) {
        Users user = userService.getUserByUserId(userId);
        UserResp userResp = new UserResp();
        userResp.setNickname(user.getNickname());
        userResp.setUserId(user.getUserId());
        return new ResponseEntity<>(userResp, HttpStatus.OK);
    }
    
    @DeleteMapping("/{userId}")
    @Transactional
    public ResponseEntity<String> removeUser(@PathVariable String userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
    @PutMapping("/nickname/{userId}")
    @Transactional
    public ResponseEntity<String> modifyUserNickname(@PathVariable String userId,
    @RequestBody UserUpdateNicknameReq userDTO) {
        if (userId.equals(userDTO.getUserId()) == false) {
            return new ResponseEntity<>("Fail", HttpStatus.UNAUTHORIZED);
        }
        userService.modifyUserNickname(userDTO);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}