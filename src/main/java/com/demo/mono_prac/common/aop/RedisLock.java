package com.demo.mono_prac.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    
    String lockFormat();

    /*
     * timeUnit is SECONDS
     */
    int leaseTime() default 3 ;

    /*
     * timeUnit is SECONDS
     */
    int waitTime() default 5 ;
}
