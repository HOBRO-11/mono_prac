package com.demo.mono_prac.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private String seatRow;
    private int startSeatColumn;
    private int endSeatColumn;
}
