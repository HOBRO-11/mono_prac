package com.demo.mono_prac.api.request;

import java.util.List;

import com.demo.mono_prac.common.model.Seat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketInfoCreateReq {
    private String title;
    private List<Seat> availableSeat;

}
