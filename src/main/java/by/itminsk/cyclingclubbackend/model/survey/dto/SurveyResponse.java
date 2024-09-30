package by.itminsk.cyclingclubbackend.model.survey.dto;

import by.itminsk.cyclingclubbackend.model.question.dto.QuestionResponse;
import by.itminsk.cyclingclubbackend.model.question.dto.UpdateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.survey.VisibilityLevelEnum;

import java.util.List;

public record SurveyResponse(Long id,
                             Boolean allowMultipleAnswer,

                             Boolean allowChangeAnswer,

                             VisibilityLevelEnum visibilityLevel,

                             List<QuestionResponse> questions) {
}
