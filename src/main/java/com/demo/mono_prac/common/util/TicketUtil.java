package com.demo.mono_prac.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TicketUtil {

    private final MessageSource messageSource;
    public final String TICKET_NOT_FOUND_EXCEPTION_MESSAGE;
    public final String TICKET_ALREADY_EXIST_EXCEPTION_MESSAGE;

    public TicketUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
        TICKET_NOT_FOUND_EXCEPTION_MESSAGE = getMessage("ticket.exception.not_found");
        TICKET_ALREADY_EXIST_EXCEPTION_MESSAGE = getMessage("ticket.exception.already_exist");
    }

    private String getMessage(String path) {
        return messageSource.getMessage(path, null, LocaleContextHolder.getLocale());
    }

}
