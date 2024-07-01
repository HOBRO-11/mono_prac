package com.demo.mono_prac.db.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.demo.mono_prac.db.entity.Tickets;
import com.demo.mono_prac.db.entity.Users;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long> {
    @NonNull
    Page<Tickets> findAll(@NonNull Pageable pageable);
    @NonNull
    Page<Tickets> findAllByUsers(Users users, @NonNull Pageable pageable);
    Optional<Tickets> findByCodeAndSeatRowAndSeatColumn(String code, String seatRow, int seatColumn);
    Page<Tickets> findByCode(String code, Pageable pageable);
    boolean existsByCodeAndSeatRowAndSeatColumn(String code, String seatRow, String seatColumn);
}
