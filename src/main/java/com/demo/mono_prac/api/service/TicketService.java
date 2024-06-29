package com.demo.mono_prac.api.service;

import org.springframework.data.domain.Page;

import com.demo.mono_prac.api.request.TicketSerialNumReq;
import com.demo.mono_prac.db.entity.Tickets;
import com.demo.mono_prac.db.entity.Users;

public interface TicketService {
    Tickets createTicket(TicketSerialNumReq ticketSerialNumReq, Users users);
    Tickets getTicketById(Long id);
    Tickets getTicketBySerialNum(TicketSerialNumReq ticketSerialNumReq);
    Page<Tickets> getTicketByCode(String code, int page, int size);
    void removeTicket(TicketSerialNumReq ticketSerialNumReq);
}
