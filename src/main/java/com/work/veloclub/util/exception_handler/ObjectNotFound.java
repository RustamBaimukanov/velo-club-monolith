package com.work.veloclub.util.exception_handler;

public class ObjectNotFound extends RuntimeException{

    private final String message;

    public ObjectNotFound(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
