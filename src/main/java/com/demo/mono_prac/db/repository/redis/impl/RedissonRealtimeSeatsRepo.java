package com.demo.mono_prac.db.repository.redis.impl;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.demo.mono_prac.common.execption.RealtimeSeatNotExistException;
import com.demo.mono_prac.common.util.RealtimeSeatUtil;
import com.demo.mono_prac.db.repository.redis.RealtimeSeatsQuery;

@Component
public class RedissonRealtimeSeatsRepo implements RealtimeSeatsQuery {

    private final StringRedisTemplate stringRedisTemplate;
    private final String REALTIME_SEAT_PREFIX;

    public RedissonRealtimeSeatsRepo(StringRedisTemplate stringRedisTemplate, RealtimeSeatUtil realtimeSeatsUtil) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.REALTIME_SEAT_PREFIX = realtimeSeatsUtil.getRealtimeSeatsPrefix();
    }

    @Override
    public byte[] get(long ticketInfosId) {
        String key = REALTIME_SEAT_PREFIX + ticketInfosId;
        String hexString = stringRedisTemplate.opsForValue().get(key);
        if(hexString == null){
            throw new RealtimeSeatNotExistException();
        }
        byte[] bytes = hexString.getBytes();
        if (bytes.length < 0) {
            throw new RealtimeSeatNotExistException();            
        }
        return hexString.getBytes();
    }

}
