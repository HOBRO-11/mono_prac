package com.demo.mono_prac.db.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.demo.mono_prac.db.entity.TicketInfos;
import com.demo.mono_prac.db.entity.Tickets;
import com.demo.mono_prac.db.entity.Users;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long> {
    @NonNull
    Page<Tickets> findAll(@NonNull Pageable pageable);
    @NonNull
    Page<Tickets> findAllByUsers(Users users, @NonNull Pageable pageable);
    Optional<Tickets> findByTicketInfosAndSeatRowAndSeatColumn(TicketInfos ticketInfos, String seatRow, int seatColumn);
    Page<Tickets> findByTicketInfos(TicketInfos ticketInfos, Pageable pageable);
    boolean existsByTicketInfosAndSeatRowAndSeatColumn(TicketInfos ticketInfos, String seatRow, int seatColumn);
}
