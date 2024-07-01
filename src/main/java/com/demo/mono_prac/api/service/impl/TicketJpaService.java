package com.demo.mono_prac.api.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.mono_prac.api.request.TicketSerialNumReq;
import com.demo.mono_prac.api.service.TicketService;
import com.demo.mono_prac.common.aop.RedissonLock;
import com.demo.mono_prac.common.execption.TicketAlreadyExistException;
import com.demo.mono_prac.common.execption.TicketNotFoundException;
import com.demo.mono_prac.db.entity.Tickets;
import com.demo.mono_prac.db.entity.Users;
import com.demo.mono_prac.db.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketJpaService implements TicketService {

    private final TicketRepository ticketRepository;
    private final static String TICKET_CREATE_REDISSON_LOCK_FORMAT = "#ticketSerialNumReq.getCode().concat('-').concat(#ticketSerialNumReq.getSeatRow()).concat('-').concat(#ticketSerialNumReq.getSeatColumn())";

    @Override
    @RedissonLock(lockFormat = TICKET_CREATE_REDISSON_LOCK_FORMAT)
    public Tickets createTicket(TicketSerialNumReq ticketSerialNumReq, Users user) {
        String code = ticketSerialNumReq.getCode();
        String seatRow = ticketSerialNumReq.getSeatRow();
        int seatColumn = ticketSerialNumReq.getSeatColumn();

        boolean isPresent = ticketRepository.findByCodeAndSeatRowAndSeatColumn(code, seatRow, seatColumn).isPresent();
        if (isPresent) {
            throw new TicketAlreadyExistException();
        }
        
        Tickets tickets = new Tickets();
        tickets.setCode(code);
        tickets.setSeatRow(seatRow);
        tickets.setSeatColumn(seatColumn);
        tickets.setUsers(user);
        ticketRepository.save(tickets);
        return tickets;
    }

    @Override
    public Page<Tickets> getTicketByCode(String code, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ticketRepository.findByCode(code, pageable);
    }

    @Override
    public Tickets getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException());
    }

    @Override
    public Tickets getTicketBySerialNum(TicketSerialNumReq ticketSerialNumReq) {
        String code = ticketSerialNumReq.getCode();
        String seatRow = ticketSerialNumReq.getSeatRow();
        int seatColumn = ticketSerialNumReq.getSeatColumn();
        return ticketRepository.findByCodeAndSeatRowAndSeatColumn(code, seatRow, seatColumn)
                .orElseThrow(() -> new TicketNotFoundException());
    }

    @Override
    public void removeTicket(TicketSerialNumReq ticketSerialNumReq) {
        String code = ticketSerialNumReq.getCode();
        String seatRow = ticketSerialNumReq.getSeatRow();
        int seatColumn = ticketSerialNumReq.getSeatColumn();
        Tickets ticket = ticketRepository.findByCodeAndSeatRowAndSeatColumn(code, seatRow, seatColumn)
                .orElseThrow(() -> new TicketNotFoundException());
        ticketRepository.delete(ticket);
    }

}
