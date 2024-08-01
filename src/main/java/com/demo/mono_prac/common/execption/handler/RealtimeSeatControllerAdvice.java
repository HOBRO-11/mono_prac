package com.demo.mono_prac.common.execption.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.mono_prac.common.execption.RealtimeSeatNotExistException;
import com.demo.mono_prac.common.util.RealtimeSeatUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.demo.mono_prac")
@RequiredArgsConstructor
public class RealtimeSeatControllerAdvice {
    
    private final RealtimeSeatUtil realtimeSeatUtil;

    @ExceptionHandler
    public ResponseEntity<String> realtimeSeatNotExistHandler(RealtimeSeatNotExistException ex) {
        String errorResult = getMessage(ex, realtimeSeatUtil.REALTIME_SEAT_NOT_EXISTS_EX_MESSAGE);
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
