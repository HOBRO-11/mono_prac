package com.demo.mono_prac.common.execption;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException() {
        super();
    }

    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

    public TicketNotFoundException(Throwable ex) {
        super(ex);
    }

}
