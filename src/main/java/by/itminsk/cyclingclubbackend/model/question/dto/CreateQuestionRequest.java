package by.itminsk.cyclingclubbackend.model.question.dto;

import by.itminsk.cyclingclubbackend.model.answer.dto.CreateAnswerRequest;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CreateQuestionRequest(
        @Schema(example = "Вопрос 1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String question,

        List<CreateAnswerRequest> answers) {
}
