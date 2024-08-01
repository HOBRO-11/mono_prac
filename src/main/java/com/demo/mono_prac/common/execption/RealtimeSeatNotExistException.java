package com.demo.mono_prac.common.execption;

public class RealtimeSeatNotExistException extends RuntimeException {
    public RealtimeSeatNotExistException() {
        super();
    }

    public RealtimeSeatNotExistException(String message) {
        super(message);
    }

    public RealtimeSeatNotExistException(String message, Throwable e) {
        super(message, e);
    }

    public RealtimeSeatNotExistException(Throwable e) {
        super(e);
    }

}
