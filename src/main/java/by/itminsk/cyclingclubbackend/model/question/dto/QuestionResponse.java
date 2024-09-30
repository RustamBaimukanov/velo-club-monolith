package by.itminsk.cyclingclubbackend.model.question.dto;

import by.itminsk.cyclingclubbackend.model.answer.dto.AnswerResponse;

import java.util.List;

public record QuestionResponse(Long id, String question,

                               List<AnswerResponse> answers) {
}
