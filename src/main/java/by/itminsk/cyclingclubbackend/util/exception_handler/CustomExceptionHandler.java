package by.itminsk.cyclingclubbackend.util.exception_handler;

import by.itminsk.cyclingclubbackend.util.exception_handler.error.ErrorContent;
import by.itminsk.cyclingclubbackend.util.exception_handler.error.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    protected ErrorResponse handleNotFound(final UsernameNotFoundException ex) {
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {AuthenticationException.class})
    protected ErrorResponse handleAuthenticationException(final AuthenticationException ex) {
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {RestoreUserNotFound.class})
    protected ErrorResponse restoreUserNotFount(final RestoreUserNotFound ex) {
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ObjectNotFound.class})
    protected ErrorResponse objectNotFound(final ObjectNotFound ex) {
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ErrorResponse violationException(final ConstraintViolationException ex) {

        return new ErrorResponse(ex.getConstraintViolations().stream().map(violation-> new ErrorContent(violation.getPropertyPath().toString(), violation.getMessage())).collect(Collectors.toList()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UnacceptableDataException.class})
    protected ErrorResponse unacceptableException(final UnacceptableDataException ex) {
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UniqueObjectExistException.class})
    protected ErrorResponse uniqueObjectExistException(final UniqueObjectExistException ex) {
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

}

