package com.demo.mono_prac.common.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.mono_prac.common.model.Seat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomMapper {

    private final ObjectMapper mapper;

    public List<Seat> readValue(String availableSeat, TypeReference<List<Seat>> typeReference)
            throws JsonMappingException, JsonProcessingException {
        return mapper.readValue(availableSeat, typeReference);

    }

    public String writeToJsonString(List<Seat> availableSeat) throws JsonProcessingException {
        return mapper.writeValueAsString(availableSeat);
    }

}
