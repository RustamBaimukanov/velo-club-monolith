package by.itminsk.cyclingclubbackend.util.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {UsernameNotFoundException.class, AuthenticationException.class})
    protected ResponseEntity<?> handleNotFound(final AuthenticationException ex, final WebRequest request) {
        return new ResponseEntity<>("Некорректный номер телефона или пароль",HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {RestoreUserNotFound.class})
    protected ResponseEntity<?> restoreUserNotFount(final RestoreUserNotFound ex) {
        return new ResponseEntity<>("Такой пользователь не существует",HttpStatus.NOT_ACCEPTABLE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ObjectNotFound.class})
    protected ResponseEntity<?> objectNotFound(final ObjectNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

