package com.demo.mono_prac.common.execption;

public class TicketInfoAlreadyExistException extends RuntimeException {
    public TicketInfoAlreadyExistException() {
        super();
    }

    public TicketInfoAlreadyExistException(String message) {
        super(message);
    }

    public TicketInfoAlreadyExistException(String message, Throwable e) {
        super(message, e);
    }

    public TicketInfoAlreadyExistException(Throwable e) {
        super(e);
    }
}
