package com.work.veloclub.util.exception_handler;

public class ExpiredTokenException extends RuntimeException{

    private final String message;

    public ExpiredTokenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
