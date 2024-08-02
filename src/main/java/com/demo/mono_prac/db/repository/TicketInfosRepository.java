package com.demo.mono_prac.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.mono_prac.db.entity.TicketInfos;

@Repository
public interface TicketInfosRepository extends JpaRepository<TicketInfos, Long>{
    Page<TicketInfos> findByTitleContaining(String title, Pageable pageable);
    boolean existsByTitle(String title);
}
