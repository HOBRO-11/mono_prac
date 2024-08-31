package com.demo.mono_prac.db.repository;

import java.util.List;
import com.demo.mono_prac.api.dto.TicketUserDto;

public interface TicketUserRepository {
    
    List<TicketUserDto> search(String title, int page, int size);

    Long count(String title);
}
