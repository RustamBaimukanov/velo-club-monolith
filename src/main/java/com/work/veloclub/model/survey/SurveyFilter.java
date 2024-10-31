package com.work.veloclub.model.survey;

import java.time.LocalDate;

public record SurveyFilter(String title,
                           Boolean allowChangeAnswer,
                           LocalDate startDate,
                           LocalDate endDate,
                           SurveyResultVisibilityEnum resultVisibility,
                           SurveyStatusEnum surveyStatus,
                           SurveyUserVisibilityEnum userVisibility) {
}
