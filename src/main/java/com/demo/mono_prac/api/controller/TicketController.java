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
import com.demo.mono_prac.api.request.TicketInfoReq;
import com.demo.mono_prac.api.request.TicketSeatReq;
import com.demo.mono_prac.api.response.TicketResp;
import com.demo.mono_prac.api.service.TicketInfoService;
import com.demo.mono_prac.api.service.TicketService;
import com.demo.mono_prac.api.service.UserService;
import com.demo.mono_prac.common.aop.RedissonLock;
import com.demo.mono_prac.db.entity.TicketInfos;
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
    private final TicketInfoService ticketInfoService;
    private final static String TICKET_CREATE_REDISSON_LOCK_FORMAT = "#ticketCreateReq.ticketId +'-'+ #ticketCreateReq.seatRow + '-' + #ticketCreateReq.seatColumn";

    @PostMapping
    @RedissonLock(lockFormat = TICKET_CREATE_REDISSON_LOCK_FORMAT)
    ResponseEntity<String> createTicket(@RequestBody @Valid TicketCreateReq ticketCreateReq) {
        String userId = ticketCreateReq.getUserId();
        Users user = userService.getUserByUserId(userId);

        Long ticketId = ticketCreateReq.getTicketId();
        TicketInfos ticketInfo = ticketInfoService.getTicketInfoById(ticketId);

        TicketInfoReq ticketInfoReq = getTicketInfoReq(ticketCreateReq);
        boolean seatAvailable = ticketInfoService.isSeatAvailable(ticketInfoReq);

        Tickets ticket = null;

        if (seatAvailable) {
            TicketSeatReq ticketSeatReq = getTicketSeatReq(ticketCreateReq);
            ticket = ticketService.createTicket(ticketSeatReq, user, ticketInfo);
        }

        if (ticket == null) {
            return new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<TicketResp> getTicketInfo(@RequestBody @Valid TicketInfoReq ticketInfoReq) {
        Long ticketId = ticketInfoReq.getTicketId();
        TicketInfos ticketInfo = ticketInfoService.getTicketInfoById(ticketId);

        TicketSeatReq ticketSeatReq = getTicketSeatReq(ticketInfoReq);
        ticketService.getTicketBySeatAndTicketInfo(ticketSeatReq, ticketInfo);

        TicketResp ticketResp = getTicketResp(ticketInfoReq);
        return new ResponseEntity<>(ticketResp, HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    ResponseEntity<String> removeTicket(@RequestBody @Valid TicketInfoReq ticketInfoReq) {
        Long ticketId = ticketInfoReq.getTicketId();
        TicketInfos ticketInfo = ticketInfoService.getTicketInfoById(ticketId);

        TicketSeatReq ticketSeatReq = getTicketSeatReq(ticketInfoReq);
        ticketService.removeTicket(ticketSeatReq, ticketInfo);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    private TicketSeatReq getTicketSeatReq(TicketInfoReq ticketInfoReq) {
        String seatRow = ticketInfoReq.getSeatRow();
        int seatColumn = ticketInfoReq.getSeatColumn();
        TicketSeatReq ticketSeatReq = new TicketSeatReq();
        ticketSeatReq.setSeatRow(seatRow);
        ticketSeatReq.setSeatColumn(seatColumn);
        return ticketSeatReq;
    }

    private TicketSeatReq getTicketSeatReq(TicketCreateReq ticketCreateReq) {
        String seatRow = ticketCreateReq.getSeatRow();
        int seatColumn = ticketCreateReq.getSeatColumn();
        TicketSeatReq ticketSeatReq = new TicketSeatReq();
        ticketSeatReq.setSeatRow(seatRow);
        ticketSeatReq.setSeatColumn(seatColumn);
        return ticketSeatReq;
    }

    private TicketResp getTicketResp(TicketInfoReq ticketInfoReq) {
        Long ticketId = ticketInfoReq.getTicketId();
        String seatRow = ticketInfoReq.getSeatRow();
        int seatColumn = ticketInfoReq.getSeatColumn();
        TicketResp ticketResp = new TicketResp();
        ticketResp.setTicketId(ticketId);
        ticketResp.setSeatRow(seatRow);
        ticketResp.setSeatColumn(seatColumn);
        return ticketResp;
    }

    private TicketInfoReq getTicketInfoReq(TicketCreateReq ticketCreateReq) {
        Long ticketId = ticketCreateReq.getTicketId();
        String seatRow = ticketCreateReq.getSeatRow();
        int seatColumn = ticketCreateReq.getSeatColumn();

        TicketInfoReq ticketInfoReq = new TicketInfoReq();
        ticketInfoReq.setTicketId(ticketId);
        ticketInfoReq.setSeatRow(seatRow);
        ticketInfoReq.setSeatColumn(seatColumn);

        return ticketInfoReq;
    }
}
