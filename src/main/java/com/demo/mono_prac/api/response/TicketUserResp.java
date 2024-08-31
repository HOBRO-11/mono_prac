package com.demo.mono_prac.api.response;

import com.demo.mono_prac.api.dto.TicketUserDto;

import lombok.Data;

@Data
public class TicketUserResp {

    private Long usersId;
    private String userId;
    private String nickname;
    private Long ticketId;
    private String title;
    private String seatRow;
    private int seatCol;

    public TicketUserResp() {
    }

    public TicketUserResp(Long usersId, String userId, String nickname, Long ticketId, String title, String seatRow,
            int seatCol) {
        this.usersId = usersId;
        this.userId = userId;
        this.nickname = nickname;
        this.ticketId = ticketId;
        this.title = title;
        this.seatRow = seatRow;
        this.seatCol = seatCol;
    }

    public TicketUserResp(TicketUserDto dto, String title){
        this.usersId = dto.getUsersId();
        this.userId = dto.getUserId();
        this.nickname = dto.getNickname();
        this.ticketId = dto.getTicketId();
        this.title = title;
        this.seatRow = dto.getSeatRow();
        this.seatCol = dto.getSeatCol();
    }

}
