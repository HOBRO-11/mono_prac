package com.demo.mono_prac.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.mono_prac.api.request.TicketInfoCreateReq;
import com.demo.mono_prac.api.request.TicketInfoReq;
import com.demo.mono_prac.api.service.TicketInfoService;
import com.demo.mono_prac.common.execption.TicketInfoCantAcceptException;
import com.demo.mono_prac.common.execption.TicketInfoNotExistsException;
import com.demo.mono_prac.common.model.Seat;
import com.demo.mono_prac.common.util.CustomMapper;
import com.demo.mono_prac.db.entity.TicketInfos;
import com.demo.mono_prac.db.repository.TicketInfosRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketInfoJpaService implements TicketInfoService {

    private final TicketInfosRepository ticketInfosRepository;
    private final CustomMapper mapper;

    @Override
    public TicketInfos createTicketInfo(TicketInfoCreateReq ticketInfoCreateReq) throws BadRequestException {
        String title = ticketInfoCreateReq.getTitle();
        String json = null;
        try {
            List<Seat> availableSeat = ticketInfoCreateReq.getAvailableSeat();
            json = mapper.writeToJsonString(availableSeat);
        } catch (JsonProcessingException e) {
            throw new TicketInfoCantAcceptException();
        }
        TicketInfos ticketInfos = new TicketInfos();
        ticketInfos.setTitle(title);
        ticketInfos.setAvailableSeat(json);
        ticketInfosRepository.save(ticketInfos);
        return ticketInfos;
    }

    @Override
    public Page<TicketInfos> getTicketInfoByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ticketInfosRepository.findByTitleContaining(title, pageable);
    }

    @Override
    public TicketInfos getTicketInfoById(Long id) {
        return ticketInfosRepository.findById(id).orElseThrow(() -> new TicketInfoNotExistsException());
    }

    @Override
    public void removeTicketInfo(Long id) {
        TicketInfos ticketInfo = ticketInfosRepository.findById(id)
                .orElseThrow(() -> new TicketInfoNotExistsException());
        ticketInfosRepository.delete(ticketInfo);
    }

    @Override
    public boolean isSeatAvailable(TicketInfoReq ticketInfoReq) {
        String seatRow = ticketInfoReq.getSeatRow();
        int seatColumn = ticketInfoReq.getSeatColumn();
        Long ticketInfoId = ticketInfoReq.getTicketId();
        TicketInfos ticketInfo = ticketInfosRepository.findById(ticketInfoId)
                .orElseThrow(() -> new TicketInfoNotExistsException());
        String json = ticketInfo.getAvailableSeat();
        List<Seat> value = new ArrayList<>();
        try {
            value = mapper.readValue(json, new TypeReference<List<Seat>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new TicketInfoCantAcceptException();
        }
        for (Seat seat : value) {
            String row = seat.getSeatRow();
            if (seatRow.equals(row)) {
                int startColumn = seat.getStartSeatColumn();
                int endColumn = seat.getEndSeatColumn();
                if (startColumn <= seatColumn && seatColumn <= endColumn) {
                    return true;
                }
            }
        }
        return false;
    }

}
