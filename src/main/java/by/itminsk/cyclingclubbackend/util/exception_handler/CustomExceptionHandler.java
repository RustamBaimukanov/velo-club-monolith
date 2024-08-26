package by.itminsk.cyclingclubbackend.util.exception_handler;

import by.itminsk.cyclingclubbackend.util.exception_handler.error.ErrorContent;
import by.itminsk.cyclingclubbackend.util.exception_handler.error.ErrorResponse;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    protected ErrorResponse handleNotFound(final UsernameNotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {AuthenticationException.class})
    protected ErrorResponse handleAuthenticationException(final AuthenticationException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {RestoreUserNotFound.class})
    protected ErrorResponse restoreUserNotFount(final RestoreUserNotFound ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ObjectNotFound.class})
    protected ErrorResponse objectNotFound(final ObjectNotFound ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ErrorResponse violationException(final ConstraintViolationException ex) {
        return new ErrorResponse(ex.getConstraintViolations().stream().map(violation-> {
            log.error(violation.getMessage());
            return new ErrorContent(violation.getPropertyPath().toString(), violation.getMessage());
        }).collect(Collectors.toList()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ErrorResponse methodValidationException(final MethodArgumentNotValidException ex) {
        return new ErrorResponse(ex.getFieldErrors().stream().map(violation-> {
            log.error(violation.getDefaultMessage());
            return new ErrorContent(violation.getField(), violation.getDefaultMessage());
        }).collect(Collectors.toList()));
    }

    //MethodArgumentNotValidException
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UnacceptableDataException.class})
    protected ErrorResponse unacceptableException(final UnacceptableDataException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UniqueObjectExistException.class})
    protected ErrorResponse uniqueObjectExistException(final UniqueObjectExistException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ExpiredJwtException.class})
    protected ErrorResponse expiredTokenException(final ExpiredJwtException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {PermissionException.class})
    protected ErrorResponse permissionException(final PermissionException ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(new ErrorContent("", ex.getMessage()));
    }

}

