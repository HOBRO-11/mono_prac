package com.demo.mono_prac.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mono_prac.api.response.TicketUserResp;
import com.demo.mono_prac.api.service.TicketUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ticket-user")
@RequiredArgsConstructor
public class TicketUserController {

    private final TicketUserService ticketUserService;

    @GetMapping
    public ResponseEntity<Page<TicketUserResp>> searchWithTitle(String title, int p, int s) {
        Page<TicketUserResp> content = ticketUserService.search(title, p, s);
        if(content.getNumberOfElements() == 0){
            return new ResponseEntity<>(content, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

}
