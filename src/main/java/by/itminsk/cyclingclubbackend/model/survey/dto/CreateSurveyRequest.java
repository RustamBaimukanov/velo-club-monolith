package by.itminsk.cyclingclubbackend.model.survey.dto;

import by.itminsk.cyclingclubbackend.model.question.dto.CreateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.survey.SurveyResultVisibilityEnum;
import by.itminsk.cyclingclubbackend.model.survey.SurveyStatusEnum;
import by.itminsk.cyclingclubbackend.model.survey.SurveyUserVisibilityEnum;

import java.time.LocalDate;
import java.util.List;

public record CreateSurveyRequest(

        String title,

        Boolean allowChangeAnswer,

        LocalDate startDate,

        LocalDate endDate,

        SurveyResultVisibilityEnum resultVisibility,

        SurveyStatusEnum surveyStatus,

        SurveyUserVisibilityEnum userVisibility,

        List<CreateQuestionRequest> questions

) {


}
