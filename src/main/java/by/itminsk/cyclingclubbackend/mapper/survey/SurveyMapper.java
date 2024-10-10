package by.itminsk.cyclingclubbackend.mapper.survey;

import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.survey.Survey;
import by.itminsk.cyclingclubbackend.model.survey.dto.CreateSurveyRequest;
import by.itminsk.cyclingclubbackend.model.survey.dto.SurveyResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true),
        imports = LocalDate.class,
        uses = QuestionMapper.class)
public interface SurveyMapper {

    @AfterMapping
    default void useSurveyQuestionSync(CreateSurveyRequest createSurveyRequest, @MappingTarget Survey survey) {
        List<Question> questions = Mappers.getMapper(QuestionMapper.class).createQuestionRequestListToQuestionList(createSurveyRequest.questions());
        for (Question question :
                questions) {
            survey.addQuestion(question);
        }
    }

    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDate.now())")
    Survey createSurveyRequestToSurvey(CreateSurveyRequest createSurveyRequest);


    SurveyResponse surveyToSurveyResponse(Survey survey);

    List<SurveyResponse> surveyListToSurveyResponseList(List<Survey> surveys);

}
