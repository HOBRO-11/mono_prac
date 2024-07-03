package com.demo.mono_prac.common.execption;

public class TicketInfoNotExistsException extends RuntimeException {

    public TicketInfoNotExistsException(String message) {
        super(message);
    }

    public TicketInfoNotExistsException(String message, Throwable ex) {
        super(message, ex);
    }

    public TicketInfoNotExistsException(Throwable ex) {
        super(ex);
    }

    public TicketInfoNotExistsException() {
        super();
    }
}
