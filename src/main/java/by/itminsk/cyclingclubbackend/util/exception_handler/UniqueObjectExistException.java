package by.itminsk.cyclingclubbackend.util.exception_handler;

public class UniqueObjectExistException extends RuntimeException {

    private final String message;

    public UniqueObjectExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;

    }
}