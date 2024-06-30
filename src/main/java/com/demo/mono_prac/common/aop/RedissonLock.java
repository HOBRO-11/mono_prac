package com.demo.mono_prac.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLock {
    
    String lockFormat();

    long leaseTime() default 3L ;

    long waitTime() default 5L ;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
