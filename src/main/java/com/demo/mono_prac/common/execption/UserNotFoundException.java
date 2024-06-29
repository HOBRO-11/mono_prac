package com.demo.mono_prac.common.execption;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message, Throwable ex){
        super(message, ex);
    }
    
    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(Throwable ex){
        super(ex);
    }

    public UserNotFoundException(){
        super();
    }
}
