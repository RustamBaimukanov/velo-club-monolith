package by.itminsk.cyclingclubbackend.util.exception_handler.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
public class ErrorResponse {

    private ErrorContent error;

    private List<ErrorContent> errors;


    public ErrorResponse(ErrorContent error) {
        this.error = error;
    }

    public ErrorResponse(List<ErrorContent> errors) {
        this.errors = errors;
    }
}


