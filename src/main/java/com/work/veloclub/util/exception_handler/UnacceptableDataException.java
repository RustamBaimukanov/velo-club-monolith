package com.work.veloclub.util.exception_handler;

public class UnacceptableDataException extends RuntimeException {

    private final String message;

    public UnacceptableDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
