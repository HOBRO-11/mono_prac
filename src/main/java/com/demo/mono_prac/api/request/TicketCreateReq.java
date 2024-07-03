package com.demo.mono_prac.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCreateReq {
    @Email
    private String userId;
    private Long ticketId;
    @Pattern(regexp = "^[A-Za-z]$")
    private String seatRow;
    private int seatColumn;
}
