package com.demo.mono_prac.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.mono_prac.api.dto.TicketUserDto;
import com.demo.mono_prac.api.response.TicketUserResp;
import com.demo.mono_prac.api.service.TicketUserService;
import com.demo.mono_prac.db.repository.TicketUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketUserQueryDslService implements TicketUserService {

    private final TicketUserRepository ticketUserRepository;

    @Override
    public Page<TicketUserResp> search(String title, int page, int size) {

        if (title == null || title.isBlank()) {
            return Page.empty();
        }
        List<TicketUserDto> contents = ticketUserRepository.search(title, page, size);
        if (contents.isEmpty()) {
            return Page.empty();
        }
        Long count = ticketUserRepository.count(title);

        Pageable pageable = PageRequest.of(page, size);

        List<TicketUserResp> collect = contents.stream().map(t -> new TicketUserResp(t, title))
                .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, count);
    }

}
