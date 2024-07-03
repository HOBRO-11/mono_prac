package com.demo.mono_prac.api.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.mono_prac.api.request.TicketSeatReq;
import com.demo.mono_prac.api.service.TicketService;
import com.demo.mono_prac.common.execption.TicketAlreadyExistException;
import com.demo.mono_prac.common.execption.TicketNotFoundException;
import com.demo.mono_prac.db.entity.TicketInfos;
import com.demo.mono_prac.db.entity.Tickets;
import com.demo.mono_prac.db.entity.Users;
import com.demo.mono_prac.db.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketJpaService implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Tickets createTicket(TicketSeatReq ticketSeatReq, Users user, TicketInfos ticketInfos) {
        String seatRow = ticketSeatReq.getSeatRow();
        int seatColumn = ticketSeatReq.getSeatColumn();

        boolean isPresent = ticketRepository.existsByTicketInfosAndSeatRowAndSeatColumn(ticketInfos, seatRow,
                seatColumn);
        if (isPresent) {
            throw new TicketAlreadyExistException();
        }

        Tickets tickets = new Tickets();
        tickets.setTicketInfos(ticketInfos);
        tickets.setSeatRow(seatRow);
        tickets.setSeatColumn(seatColumn);
        tickets.setUsers(user);
        ticketRepository.save(tickets);

        user.addTicket(tickets);
        ticketInfos.addTickets(tickets);

        ticketRepository.save(tickets);
        return tickets;
    }

    @Override
    public Page<Tickets> getTicketByTicketInfo(TicketInfos ticketInfos, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ticketRepository.findByTicketInfos(ticketInfos, pageable);
    }

    @Override
    public Tickets getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException());
    }

    @Override
    public Tickets getTicketBySeatAndTicketInfo(TicketSeatReq ticketSeatReq, TicketInfos ticketInfos) {
        String seatRow = ticketSeatReq.getSeatRow();
        int seatColumn = ticketSeatReq.getSeatColumn();
        return ticketRepository.findByTicketInfosAndSeatRowAndSeatColumn(ticketInfos, seatRow, seatColumn)
                .orElseThrow(() -> new TicketNotFoundException());
    }

    @Override
    public void removeTicket(TicketSeatReq ticketSeatReq, TicketInfos ticketInfos) {
        String seatRow = ticketSeatReq.getSeatRow();
        int seatColumn = ticketSeatReq.getSeatColumn();
        Tickets ticket = ticketRepository.findByTicketInfosAndSeatRowAndSeatColumn(ticketInfos, seatRow, seatColumn)
                .orElseThrow(() -> new TicketNotFoundException());
        ticketRepository.delete(ticket);
    }

}
