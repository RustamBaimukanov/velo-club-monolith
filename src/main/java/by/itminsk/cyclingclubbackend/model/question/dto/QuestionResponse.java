package by.itminsk.cyclingclubbackend.model.question.dto;

import by.itminsk.cyclingclubbackend.model.answer.dto.AnswerResponse;
import by.itminsk.cyclingclubbackend.model.answer.dto.CreateAnswerRequest;

import java.util.List;

public record QuestionResponse(

        Long id,
        String question,
        Boolean allowMultipleAnswer,
        Boolean isRequired,
        List<AnswerResponse> answers) {
}
