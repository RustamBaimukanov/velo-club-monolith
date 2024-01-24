package by.itminsk.cyclingclubbackend.util.exception_handler;

public class RestoreUserNotFound extends Exception {
    public RestoreUserNotFound(String errorMessage) {
        super(errorMessage);
    }
}
