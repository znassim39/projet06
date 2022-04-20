package com.salimahafirassou.paymybuddy.exception;

/**
 * Exception thrown by system in case some one try to register with already exisiting email
 * id in the system.
 */
public class PasswordDoesNotMatchException extends Exception {

    public PasswordDoesNotMatchException() {
        super();
    }


    public PasswordDoesNotMatchException(String message) {
        super(message);
    }


    public PasswordDoesNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}