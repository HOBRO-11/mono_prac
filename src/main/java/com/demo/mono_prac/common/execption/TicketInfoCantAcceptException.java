package com.demo.mono_prac.common.execption;

public class TicketInfoCantAcceptException extends RuntimeException {

    public TicketInfoCantAcceptException() {
        super();
    }

    public TicketInfoCantAcceptException(String message, Throwable e) {
        super(message, e);
    }

    public TicketInfoCantAcceptException(String message) {
        super(message);
    }

    public TicketInfoCantAcceptException(Throwable e) {
        super(e);
    }
}

