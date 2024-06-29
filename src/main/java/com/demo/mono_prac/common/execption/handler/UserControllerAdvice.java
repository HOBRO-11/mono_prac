package com.demo.mono_prac.common.execption.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.mono_prac.common.execption.UserNotFoundException;
import com.demo.mono_prac.common.util.UserUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.demo.mono_prac")
@RequiredArgsConstructor
public class UserControllerAdvice {

    private final UserUtil userUtil;

    @ExceptionHandler
    public ResponseEntity<String> userExHandler(UserNotFoundException ex) {
        String errorResult = getMessage(ex, userUtil.USER_NOT_FOUND_EXCEPTION_MESSAGE);
        log.error(errorResult);
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    private String getMessage(RuntimeException ex, String defaultMessage) {
        if (ex.getMessage() == null) {
            return defaultMessage;
        }
        return ex.getMessage();
    }
}
