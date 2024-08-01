package com.demo.mono_prac.api.service;

import java.util.List;

public interface RealtimeSeatService {

    List<Boolean> getRealTimeSeat(long ticketInfosId);

}