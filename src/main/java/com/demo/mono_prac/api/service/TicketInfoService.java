package com.demo.mono_prac.api.service;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;

import com.demo.mono_prac.api.request.TicketInfoCreateReq;
import com.demo.mono_prac.api.request.TicketInfoReq;
import com.demo.mono_prac.db.entity.TicketInfos;

public interface TicketInfoService {
    TicketInfos createTicketInfo(TicketInfoCreateReq ticketInfoCreateReq) throws BadRequestException;
    Page<TicketInfos> getTicketInfoByTitle(String title, int page, int size);
    boolean isSeatAvailable(TicketInfoReq ticketInfoReq);
    TicketInfos getTicketInfoById(Long id);
    void removeTicketInfo(Long id);

}
