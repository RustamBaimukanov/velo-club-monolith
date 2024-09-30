package by.itminsk.cyclingclubbackend.model.question.dto;

import by.itminsk.cyclingclubbackend.model.answer.dto.CreateAnswerRequest;
import by.itminsk.cyclingclubbackend.model.answer.dto.UpdateAnswerRequest;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record UpdateQuestionRequest(Long id,
                                    @Schema(example = "Вопрос 1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                                    String question,

                                    List<UpdateAnswerRequest> answers) {
}
