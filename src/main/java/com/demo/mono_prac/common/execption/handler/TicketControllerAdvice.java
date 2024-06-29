package com.demo.mono_prac.common.execption.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.mono_prac.common.execption.TicketAlreadyExistException;
import com.demo.mono_prac.common.execption.TicketNotFoundException;
import com.demo.mono_prac.common.util.TicketUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.demo.mono_prac")
@RequiredArgsConstructor
public class TicketControllerAdvice {

    private final TicketUtil ticketUtil;

    @ExceptionHandler
    public ResponseEntity<String> ticketNotFoundExHandler(TicketNotFoundException ex) {
        String errorResult = getMessage(ex, ticketUtil.TICKET_NOT_FOUND_EXCEPTION_MESSAGE);
        log.error(errorResult);
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> ticketAlreadyExistExHandler(TicketAlreadyExistException ex){
        String errorResult = getMessage(ex, ticketUtil.TICKET_ALREADY_EXIST_EXCEPTION_MESSAGE);
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
