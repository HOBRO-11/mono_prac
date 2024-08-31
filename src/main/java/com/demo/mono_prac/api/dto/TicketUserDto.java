package com.demo.mono_prac.api.dto;

import lombok.Data;

@Data
public class TicketUserDto {

    private Long usersId;
    private String userId;
    private String nickname;
    private Long ticketId;
    private Long ticketInfosId;
    private String seatRow;
    private int seatCol;

    public TicketUserDto() {
    }

    public TicketUserDto(Long usersId, String userId, String nickname, Long ticketId, Long ticketInfosId,
            String seatRow, int seatCol) {
        this.usersId = usersId;
        this.userId = userId;
        this.nickname = nickname;
        this.ticketId = ticketId;
        this.ticketInfosId = ticketInfosId;
        this.seatRow = seatRow;
        this.seatCol = seatCol;
    }

}
