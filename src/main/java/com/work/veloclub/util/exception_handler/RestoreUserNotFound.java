package com.work.veloclub.util.exception_handler;

public class RestoreUserNotFound extends RuntimeException {
    private final String message;

    public RestoreUserNotFound(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
