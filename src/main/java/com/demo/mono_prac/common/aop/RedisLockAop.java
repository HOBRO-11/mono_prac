package com.demo.mono_prac.common.aop;

import java.lang.reflect.Method;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.demo.mono_prac.common.aop.sevice.RedisLockService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RedisLockAop {
    
    private static final String REDIS_LOCK_PREFIX = "LOCK:";
    private final AopForTransaction aopForTransaction;
    private final RedisLockService lockService;

    @Around("@annotation(com.demo.mono_prac.common.aop.RedisLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        int leaseTime = redisLock.leaseTime();
        int waitTime = redisLock.waitTime();

        String key = REDIS_LOCK_PREFIX + CustomSpelParser.getDynamicValue(signature.getParameterNames(),
                joinPoint.getArgs(), redisLock.lockFormat());

        String uuid = UUID.randomUUID().toString();

        try {
            boolean available = lockService.tryLock(key, uuid, leaseTime, waitTime);
            if (!available) {
                return null;
            }
            return aopForTransaction.proceed(joinPoint);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("detect interrupt");
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 잠금 해제 시도
                lockService.releaseLock(key, uuid);
            } catch (Exception e) {
                log.error("Failed to release lock: {}", e.getMessage(), e);
            }
        }
    }

}
