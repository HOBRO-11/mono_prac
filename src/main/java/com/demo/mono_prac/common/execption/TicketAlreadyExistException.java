package com.demo.mono_prac.common.execption;

public class TicketAlreadyExistException extends RuntimeException {

    public TicketAlreadyExistException() {
        super();
    }

    public TicketAlreadyExistException(String message) {
        super(message);
    }

    public TicketAlreadyExistException(String message, Throwable ex) {
        super(message, ex);
    }

    public TicketAlreadyExistException(Throwable ex) {
        super(ex);
    }
}
