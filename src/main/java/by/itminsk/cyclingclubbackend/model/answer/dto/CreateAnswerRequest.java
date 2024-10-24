package by.itminsk.cyclingclubbackend.model.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAnswerRequest(

        @NotNull(message = "ownOption must not be null")
        Boolean ownOption,
        @Schema(example = "Ответ 1",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @NotBlank(message = "Answer is mandatory")
        @Size(max = 50, message = "Answer must be at most 50 characters long")
                                  String answer) {}
