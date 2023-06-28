package by.itminsk.cyclingclubbackend.exception_handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {UsernameNotFoundException.class, AuthenticationException.class})
    protected ResponseEntity<?> handleNotFound(final AuthenticationException ex, final WebRequest request) {
        return new ResponseEntity<>("Некорректный номер телефона или пароль",HttpStatus.UNAUTHORIZED);
    }

}

