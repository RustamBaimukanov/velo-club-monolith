package by.itminsk.cyclingclubbackend.model.question.dto;

import by.itminsk.cyclingclubbackend.model.answer.dto.CreateAnswerRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateQuestionRequest(
        @Schema(example = "Вопрос 1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @NotBlank(message = "Question is mandatory")
        @Size(max = 50, message = "Question must be at most 50 characters long")
        String question,
        @NotNull(message = "allowMultipleAnswer must not be null")
        Boolean allowMultipleAnswer,
        @NotNull(message = "isRequired must not be null")
        Boolean isRequired,

        @NotEmpty(message = "At least one answer must be provided")
        @Valid
        List<CreateAnswerRequest> answers) {
}
