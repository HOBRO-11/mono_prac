package com.demo.mono_prac.common.execption.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.mono_prac.common.execption.TicketInfoCantAcceptException;
import com.demo.mono_prac.common.execption.TicketInfoNotExistsException;
import com.demo.mono_prac.common.util.TicketInfoUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.demo.mono_prac")
@RequiredArgsConstructor
public class TicketInfoControllerAdvice {

    private final TicketInfoUtil ticketInfoUtil;

    @ExceptionHandler
    public ResponseEntity<String> ticketInfoNotExistsExHandler(TicketInfoNotExistsException ex) {
        String errorResult = getMessage(ex, ticketInfoUtil.TICKET_INFO_NOT_EXISTS_EX_MESSAGE);
        log.error(errorResult);
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<String> ticketInfoCantAcceptExHandler(TicketInfoCantAcceptException ex) {
        String errorResult = getMessage(ex, ticketInfoUtil.TICKET_INFO_CANT_ACCEPT_EX_MESSAGE);
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
