package by.itminsk.cyclingclubbackend.mapper.survey;

import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.question.dto.QuestionResponse;
import by.itminsk.cyclingclubbackend.model.survey.Survey;
import by.itminsk.cyclingclubbackend.model.survey.dto.CreateSurveyRequest;
import by.itminsk.cyclingclubbackend.model.survey.dto.SurveyResponse;
import by.itminsk.cyclingclubbackend.model.survey.dto.UpdateSurveyRequest;

import java.util.List;
import java.util.stream.Collectors;

public class SurveyMapper {

    public static Survey mapToSurvey(CreateSurveyRequest request) {
        if (request == null) {
            return null; // или выбросьте исключение
        }

        Survey survey = new Survey();
        survey.setTitle(request.title());
        survey.setAllowChangeAnswer(request.allowChangeAnswer());
        survey.setStartDate(request.startDate());
        survey.setEndDate(request.endDate());
        survey.setResultVisibility(request.resultVisibility());
        survey.setSurveyStatus(request.surveyStatus());
        survey.setUserVisibility(request.userVisibility());

        // Обработка вопросов и установление связи
        if (request.questions() != null) {
            List<Question> questionList = request.questions().stream()
                    .map(QuestionMapper::mapToQuestion)
                    .peek(question -> question.setSurvey(survey)) // Устанавливаем связь с опросом
                    .collect(Collectors.toList());
            survey.setQuestions(questionList);
        }

        return survey;
    }

    public static Survey mapToSurvey(UpdateSurveyRequest request) {
        if (request == null) {
            return null;
        }

        Survey survey = new Survey();
        survey.setId(request.id());
        survey.setTitle(request.title());
        survey.setAllowChangeAnswer(request.allowChangeAnswer());
        survey.setStartDate(request.startDate());
        survey.setEndDate(request.endDate());
        survey.setResultVisibility(request.resultVisibility());
        survey.setSurveyStatus(request.surveyStatus());
        survey.setUserVisibility(request.userVisibility());

        // Обработка вопросов и установление связи
        if (request.questions() != null) {
            List<Question> questionList = request.questions().stream()
                    .map(QuestionMapper::mapToQuestion)
                    .peek(question -> question.setSurvey(survey)) // Устанавливаем связь с опросом
                    .collect(Collectors.toList());
            survey.setQuestions(questionList);
        }

        return survey;
    }

    public static SurveyResponse surveyToSurveyResponse(Survey survey) {
        List<QuestionResponse> questionResponses = survey.getQuestions().stream()
                .map(QuestionMapper::questionToQuestionResponse)
                .collect(Collectors.toList());

        return new SurveyResponse(
                survey.getId(),
                survey.getTitle(),
                survey.getAllowChangeAnswer(),
                survey.getStartDate(),
                survey.getEndDate(),
                survey.getResultVisibility(),
                survey.getSurveyStatus(),
                survey.getUserVisibility(),
                questionResponses
        );
    }

    public static List<SurveyResponse> surveyListToSurveyResponseList(List<Survey> surveys) {
        return surveys.stream()
                .map(SurveyMapper::surveyToSurveyResponse)
                .collect(Collectors.toList());
    }
}
