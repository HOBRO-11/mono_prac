package com.demo.mono_prac.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.mono_prac.api.service.RealtimeSeatService;
import com.demo.mono_prac.db.repository.redis.RealtimeSeatsQuery;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedissonRealtimeSeatService implements RealtimeSeatService {

    private final RealtimeSeatsQuery query;

    @Override
    public List<Boolean> getRealTimeSeat(long ticketInfosId) {
        byte[] bytes = query.get(ticketInfosId);
        return convertByteArrayToBooleanList(bytes);
    }

    public static List<Boolean> convertByteArrayToBooleanList(byte[] byteArray) {
        List<Boolean> booleanList = new ArrayList<>();
        for (byte b : byteArray) {
            for (int i = 7; i >= 0; i--) {
                booleanList.add((b & (1 << i)) != 0);
            }
        }
        return booleanList;
    }
}
