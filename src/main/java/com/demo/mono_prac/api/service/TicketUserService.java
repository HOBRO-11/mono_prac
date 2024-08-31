package com.demo.mono_prac.api.service;

import org.springframework.data.domain.Page;

import com.demo.mono_prac.api.response.TicketUserResp;

public interface TicketUserService {
        Page<TicketUserResp> search(String title, int page, int size);
}
