package com.demo.mono_prac.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mono_prac.api.request.TicketCreateReq;
import com.demo.mono_prac.api.request.TicketSerialNumReq;
import com.demo.mono_prac.api.response.TicketResp;
import com.demo.mono_prac.api.service.TicketService;
import com.demo.mono_prac.api.service.UserService;
import com.demo.mono_prac.db.entity.Tickets;
import com.demo.mono_prac.db.entity.Users;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;

    @PostMapping
    @Transactional
    ResponseEntity<String> createTicket(@RequestBody @Valid TicketCreateReq ticketCreateReq) {
        String userId = ticketCreateReq.getUserId();
        Users user = userService.getUserByUserId(userId);

        TicketSerialNumReq ticketSerialNumReq = getTicketSerialNumReq(ticketCreateReq);
        ticketService.createTicket(ticketSerialNumReq, user);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<TicketResp> getTicketInfo(@RequestBody @Valid TicketSerialNumReq ticketSerialNumReq) {
        Tickets ticket = ticketService.getTicketBySerialNum(ticketSerialNumReq);

        String code = ticket.getCode();
        String seatRow = ticket.getSeatRow();
        int seatColumn = ticket.getSeatColumn();

        TicketResp ticketResp = new TicketResp();
        ticketResp.setCode(code);
        ticketResp.setSeatRow(seatRow);
        ticketResp.setSeatColumn(seatColumn);

        return new ResponseEntity<>(ticketResp, HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    ResponseEntity<String> removeTicket(@RequestBody @Valid TicketSerialNumReq ticketSerialNumReq) {
        ticketService.removeTicket(ticketSerialNumReq);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    private TicketSerialNumReq getTicketSerialNumReq(TicketCreateReq ticketCreateReq) {
        String code = ticketCreateReq.getCode();
        String seatRow = ticketCreateReq.getSeatRow();
        int seatColumn = ticketCreateReq.getSeatColumn();
        TicketSerialNumReq ticketSerialNumReq = new TicketSerialNumReq();
        ticketSerialNumReq.setCode(code);
        ticketSerialNumReq.setSeatRow(seatRow);
        ticketSerialNumReq.setSeatColumn(seatColumn);
        return ticketSerialNumReq;
    }
}
