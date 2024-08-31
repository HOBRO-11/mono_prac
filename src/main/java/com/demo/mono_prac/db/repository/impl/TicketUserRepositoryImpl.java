package com.demo.mono_prac.db.repository.impl;

import static com.demo.mono_prac.db.entity.QTicketInfos.ticketInfos;
import static com.demo.mono_prac.db.entity.QTickets.tickets;
import static com.demo.mono_prac.db.entity.QUsers.users;
import static com.querydsl.core.types.Projections.constructor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.mono_prac.api.dto.TicketUserDto;
import com.demo.mono_prac.db.repository.TicketUserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TicketUserRepositoryImpl implements TicketUserRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TicketUserDto> search(String title, int page, int size) {

        return queryFactory
                .select(constructor(TicketUserDto.class,
                        users.id,
                        users.userId,
                        users.nickname,
                        tickets.id,
                        tickets.ticketInfos.id,
                        tickets.seatRow,
                        tickets.seatColumn))
                .from(tickets)
                .innerJoin(tickets.users, users)
                .where(tickets.ticketInfos.id.eq(
                        queryFactory
                                .select(ticketInfos.id)
                                .from(ticketInfos)
                                .where(isTitle(
                                        title))))
                .offset(page)
                .limit(size)
                .fetch();

    }

    @Override
    public Long count(String title) {
        if (title == null || title.isBlank()) {
            return 0L;
        }
        return queryFactory
                .select(tickets.count())
                .from(tickets)
                .where(tickets.ticketInfos.id.eq(
                        queryFactory
                                .select(ticketInfos.id)
                                .from(ticketInfos)
                                .where(isTitle(
                                        title))))
                .fetchOne();
    }

    private BooleanExpression isTitle(String title) {
        return title.isEmpty() ? null : ticketInfos.title.eq(title);
    }

}
