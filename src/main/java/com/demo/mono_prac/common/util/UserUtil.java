package com.demo.mono_prac.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    private final MessageSource messageSource;
    public final String USER_NOT_FOUND_EXCEPTION_MESSAGE;
    public final String USER_ALREADY_EXIST_EXCEPTION_MESSAGE;

    public UserUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
        USER_NOT_FOUND_EXCEPTION_MESSAGE = getMessage("user.exception.not_found");
        USER_ALREADY_EXIST_EXCEPTION_MESSAGE = getMessage("user.exception.already_exist");
    }

    private String getMessage(String path){
        return messageSource.getMessage(path, null, LocaleContextHolder.getLocale());
    }

}
