package com.demo.mono_prac.api.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mono_prac.api.request.TicketInfoCreateReq;
import com.demo.mono_prac.api.service.TicketInfoService;
import com.demo.mono_prac.db.entity.TicketInfos;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ticket-info")
@RequiredArgsConstructor
public class TicketInfoController {

    private final TicketInfoService ticketInfoService;

    @PostMapping
    public ResponseEntity<String> createTicketInfo(@RequestBody @Valid TicketInfoCreateReq ticketInfoCreateReq)
            throws BadRequestException {
        ticketInfoService.createTicketInfo(ticketInfoCreateReq);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TicketInfos>> getTicketInfoByTitle(@RequestParam String title, @RequestParam int page,
            @RequestParam int size) {
        Page<TicketInfos> pages = ticketInfoService.getTicketInfoByTitle(title, page, size);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> removeTicketInfo(Long id){
        ticketInfoService.removeTicketInfo(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
