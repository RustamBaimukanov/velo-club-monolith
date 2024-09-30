package by.itminsk.cyclingclubbackend.model.survey.dto;

import by.itminsk.cyclingclubbackend.model.question.dto.CreateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.survey.VisibilityLevelEnum;

import java.util.List;

public record CreateSurveyRequest(

        Boolean allowMultipleAnswer,

        Boolean allowChangeAnswer,

        VisibilityLevelEnum visibilityLevel,

        List<CreateQuestionRequest> questions
) {


}
