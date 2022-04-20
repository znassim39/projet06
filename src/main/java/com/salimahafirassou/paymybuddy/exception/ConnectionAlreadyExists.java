package com.salimahafirassou.paymybuddy.exception;

public class ConnectionAlreadyExists extends Exception {

    public ConnectionAlreadyExists() {
        super();
    }


    public ConnectionAlreadyExists(String message) {
        super(message);
    }


    public ConnectionAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
    
}
