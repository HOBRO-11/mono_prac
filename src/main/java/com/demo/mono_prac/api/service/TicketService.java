package com.demo.mono_prac.api.service;

import org.springframework.data.domain.Page;

import com.demo.mono_prac.api.request.TicketSeatReq;
import com.demo.mono_prac.db.entity.TicketInfos;
import com.demo.mono_prac.db.entity.Tickets;
import com.demo.mono_prac.db.entity.Users;

public interface TicketService {
    Tickets createTicket(TicketSeatReq ticketSeatReq, Users users, TicketInfos ticketInfos);
    Tickets getTicketById(Long id);
    Tickets getTicketBySeatAndTicketInfo(TicketSeatReq ticketSeatReq, TicketInfos ticketInfos);
    Page<Tickets> getTicketByTicketInfo(TicketInfos ticketInfos, int page, int size);
    void removeTicket(TicketSeatReq ticketSeatReq, TicketInfos ticketInfos);
}
