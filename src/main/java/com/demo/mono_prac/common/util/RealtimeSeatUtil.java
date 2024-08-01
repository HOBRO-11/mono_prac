package com.demo.mono_prac.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

@Configuration
public class RealtimeSeatUtil {

    @Value("${realtime.seats.prefix}")
    public String REALTIME_SEATS_PREFIX;
    public final String REALTIME_SEAT_NOT_EXISTS_EX_MESSAGE;
    public final MessageSource messageSource;
    
    public RealtimeSeatUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
        REALTIME_SEAT_NOT_EXISTS_EX_MESSAGE = getMessage("realtimeSeat.exception.not_exists");
    }
    
    private String getMessage(String path) {
        return messageSource.getMessage(path, null, LocaleContextHolder.getLocale());
    }
    public String getRealtimeSeatsPrefix() {
        return REALTIME_SEATS_PREFIX;
    }

}
