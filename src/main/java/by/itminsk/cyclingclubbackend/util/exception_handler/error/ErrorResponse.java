package by.itminsk.cyclingclubbackend.util.exception_handler.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final List<ErrorContent> errors;
}


