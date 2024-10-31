package com.work.veloclub.model.survey.dto;

import com.work.veloclub.model.question.dto.QuestionResponse;
import com.work.veloclub.model.survey.SurveyResultVisibilityEnum;
import com.work.veloclub.model.survey.SurveyStatusEnum;
import com.work.veloclub.model.survey.SurveyUserVisibilityEnum;

import java.time.LocalDate;
import java.util.List;

public record SurveyResponse(
        Long id,
        String title,

        Boolean allowChangeAnswer,

        LocalDate startDate,

        LocalDate endDate,

        SurveyResultVisibilityEnum resultVisibility,

        SurveyStatusEnum surveyStatus,

        SurveyUserVisibilityEnum userVisibility,

        List<QuestionResponse> questions) {
}
