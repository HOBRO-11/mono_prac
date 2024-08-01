package com.demo.mono_prac.api.response;

import java.util.List;

import com.demo.mono_prac.common.model.Seat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealtimeSeatResp {
    List<Seat> seatList;
    List<Boolean> realTimeSeat;
}
