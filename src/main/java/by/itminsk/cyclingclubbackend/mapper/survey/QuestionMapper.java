package by.itminsk.cyclingclubbackend.mapper.survey;

import by.itminsk.cyclingclubbackend.model.answer.Answer;
import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.question.dto.CreateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.question.dto.QuestionResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true),
        uses = AnswerMapper.class)
public interface QuestionMapper {


    @AfterMapping
    default void useQuestionAnswerSync(CreateQuestionRequest createQuestionRequest, @MappingTarget Question question) {
        List<Answer> answers = Mappers.getMapper(AnswerMapper.class).createAnswerRequestListToAnswerList(createQuestionRequest.answers());
        for (Answer answer :
                answers) {
            question.addAnswer(answer);
        }
    }

    @Mapping(target = "answers", ignore = true)
    Question createQuestionRequestToQuestion(CreateQuestionRequest createQuestionRequest);

    List<Question> createQuestionRequestListToQuestionList(List<CreateQuestionRequest> createQuestionRequest);

    QuestionResponse questionToQuestionResponse(Question question);

    List<QuestionResponse> questionListToQuestionResponseList(List<Question> questions);

}
