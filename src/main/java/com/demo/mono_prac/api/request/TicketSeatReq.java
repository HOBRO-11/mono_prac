package com.demo.mono_prac.api.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketSeatReq {
    @Pattern(regexp = "^[A-Za-z]$", message = "영어 한 글자만 입력가능합니다.")
    private String seatRow;
    private int seatColumn;
}
