package com.demo.mono_prac.common.aop.sevice;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisLockService {

    private final StringRedisTemplate stringRedisTemplate;
    private final static int GET_LOCK_SUCCESSFULLY = 1;
    private final static int UNLOCK_SUCCESSFULLY = 1;
    private final static int ALREADY_UNLOCK = 2;

    public boolean tryLock(String key, String uuid, int releaseTime, long waitTime) throws InterruptedException {

        Supplier<Long> invoker = () -> {
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/get-lock.lua")));
            redisScript.setResultType(Long.class);
            return stringRedisTemplate.execute(redisScript, List.of(key), String.valueOf(releaseTime), uuid);
        };

        Long result = invoker.get();

        if (result == GET_LOCK_SUCCESSFULLY) {
            return true;
        }

        if (waitTime == 0) {
            return false;
        }

        return waitAndTryLock(invoker, waitTime);
    }

    private boolean waitAndTryLock(Supplier<Long> invoker, long waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
        Long result = invoker.get();

        if (result == GET_LOCK_SUCCESSFULLY) {
            return true;
        }

        return false;
    }

    public boolean releaseLock(String key, String uuid) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/release-lock.lua")));
        redisScript.setResultType(Long.class);

        Long result = stringRedisTemplate.execute(redisScript, List.of(key), uuid);

        if (result == ALREADY_UNLOCK) {
            log.warn("this key : " + key + " already unlock");
            return true;
        }

        if (result == UNLOCK_SUCCESSFULLY) {
            return true;
        }

        return false;
    }
}
