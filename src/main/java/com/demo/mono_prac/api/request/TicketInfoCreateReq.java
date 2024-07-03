package com.demo.mono_prac.api.request;

import java.util.List;

import com.demo.mono_prac.common.model.Seat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketInfoCreateReq {
    private String title;
    private List<Seat> availableSeat;

    public String getJsonOfAvailableSeat() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(availableSeat);
        return json;
    }

    

}
