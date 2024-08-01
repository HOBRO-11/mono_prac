package com.demo.mono_prac.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mono_prac.api.response.RealtimeSeatResp;
import com.demo.mono_prac.api.service.RealtimeSeatService;
import com.demo.mono_prac.api.service.TicketInfoService;
import com.demo.mono_prac.common.execption.TicketInfoCantAcceptException;
import com.demo.mono_prac.common.model.Seat;
import com.demo.mono_prac.common.util.CustomMapper;
import com.demo.mono_prac.db.entity.TicketInfos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/realtime/seats")
@RestController
@RequiredArgsConstructor
public class RealtimeSeatController {

    private final CustomMapper mapper;
    private final TicketInfoService ticketInfoService;
    private final RealtimeSeatService realTimeSeatService;

    @GetMapping("/{ticketInfosId}")
    public ResponseEntity<RealtimeSeatResp> getRealTimeSeat(@PathVariable String ticketInfosId) {
        System.out.println(ticketInfosId);
        long longId = Long.parseLong(ticketInfosId);
        TicketInfos ticketInfo = ticketInfoService.getTicketInfoById(longId);
        String availableSeat = ticketInfo.getAvailableSeat();
        List<Seat> seatList = null;
        try {
            seatList = mapper.readValue(availableSeat, new TypeReference<List<Seat>>() {;
            });
        } catch (JsonProcessingException e) {
            throw new TicketInfoCantAcceptException(e);
        }
        List<Boolean> realTimeSeat = realTimeSeatService.getRealTimeSeat(longId);
        RealtimeSeatResp realtimeSeatResp = new RealtimeSeatResp();
        realtimeSeatResp.setSeatList(seatList);
        realtimeSeatResp.setRealTimeSeat(realTimeSeat);
        return new ResponseEntity<>(realtimeSeatResp, HttpStatus.OK); 
    }

}
