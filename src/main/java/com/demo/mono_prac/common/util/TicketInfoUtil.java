package com.demo.mono_prac.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TicketInfoUtil {

    private final MessageSource messageSource;
    public final String TICKET_INFO_NOT_EXISTS_EX_MESSAGE;
    public final String TICKET_INFO_CANT_ACCEPT_EX_MESSAGE;
    public final String TICKET_INFO_ALREADY_EXIST_EX_MESSAGE;

    public TicketInfoUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
        TICKET_INFO_NOT_EXISTS_EX_MESSAGE = getMessage("ticketInfo.exception.not_exists");
        TICKET_INFO_CANT_ACCEPT_EX_MESSAGE = getMessage("ticketInfo.exception.cant_accept");
        TICKET_INFO_ALREADY_EXIST_EX_MESSAGE = getMessage("ticketInfo.exception.already_exist");

    }

    private String getMessage(String path) {
        return messageSource.getMessage(path, null, LocaleContextHolder.getLocale());
    }

}
