package by.itminsk.cyclingclubbackend.mapper.survey;

import by.itminsk.cyclingclubbackend.model.answer.Answer;
import by.itminsk.cyclingclubbackend.model.answer.dto.AnswerResponse;
import by.itminsk.cyclingclubbackend.model.answer.dto.CreateAnswerRequest;
import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.question.dto.CreateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.question.dto.QuestionResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface AnswerMapper {

    Answer createAnswerRequestToAnswer(CreateAnswerRequest createAnswerRequest);

    List<Answer> createAnswerRequestListToAnswerList(List<CreateAnswerRequest> createAnswerRequests);

    AnswerResponse answerToAnswerResponse(Answer answer);

    List<AnswerResponse> answerListToAnswerResponseList(List<Answer> answers);
}
