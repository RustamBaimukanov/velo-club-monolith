package by.itminsk.cyclingclubbackend.model.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateAnswerRequest(
        @Schema(example = "Ответ 1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)

                                  String answer) {}