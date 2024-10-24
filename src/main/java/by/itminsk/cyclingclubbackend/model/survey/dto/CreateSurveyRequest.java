package by.itminsk.cyclingclubbackend.model.survey.dto;

import by.itminsk.cyclingclubbackend.model.question.dto.CreateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.survey.SurveyResultVisibilityEnum;
import by.itminsk.cyclingclubbackend.model.survey.SurveyStatusEnum;
import by.itminsk.cyclingclubbackend.model.survey.SurveyUserVisibilityEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record CreateSurveyRequest(
        @NotBlank(message = "Title is mandatory")
        @Size(max = 50, message = "Title must be at most 50 characters long")
        String title,

        @NotNull(message = "allowChangeAnswer must not be null")
        Boolean allowChangeAnswer,

        LocalDate startDate,
        LocalDate endDate,

        @NotNull(message = "Result visibility must not be null")
        SurveyResultVisibilityEnum resultVisibility,

        @NotNull(message = "Survey status must not be null")
        SurveyStatusEnum surveyStatus,

        @NotNull(message = "User visibility must not be null")
        SurveyUserVisibilityEnum userVisibility,

        @NotEmpty(message = "At least one question must be provided")
        @Valid
        List<CreateQuestionRequest> questions

) {


}
