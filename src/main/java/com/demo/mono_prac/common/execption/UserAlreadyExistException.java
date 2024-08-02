package com.demo.mono_prac.common.execption;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, Throwable e) {
        super(message, e);
    }

    public UserAlreadyExistException(Throwable e) {
        super(e);
    }
}
