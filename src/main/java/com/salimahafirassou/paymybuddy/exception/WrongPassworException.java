package com.salimahafirassou.paymybuddy.exception;

public class WrongPassworException extends Exception{

    public WrongPassworException() {
        super();
    }


    public WrongPassworException(String message) {
        super(message);
    }


    public WrongPassworException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
