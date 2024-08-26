package by.itminsk.cyclingclubbackend.util.exception_handler;

public class PermissionException extends RuntimeException {

    private final String message;

    public PermissionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;

    }
}