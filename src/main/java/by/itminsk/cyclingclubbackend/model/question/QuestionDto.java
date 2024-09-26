package by.itminsk.cyclingclubbackend.model.question;

import by.itminsk.cyclingclubbackend.model.answer.AnswerDto;

import java.util.List;

public record QuestionDto(Long id,

                          String question,

                          List<AnswerDto> answers) {
}
