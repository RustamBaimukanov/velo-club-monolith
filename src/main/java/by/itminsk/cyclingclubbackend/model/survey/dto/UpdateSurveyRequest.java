package by.itminsk.cyclingclubbackend.model.survey.dto;

import by.itminsk.cyclingclubbackend.model.question.dto.CreateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.question.dto.UpdateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.survey.StateEnum;
import by.itminsk.cyclingclubbackend.model.survey.SurveyResultVisibilityEnum;
import by.itminsk.cyclingclubbackend.model.survey.SurveyStatusEnum;
import by.itminsk.cyclingclubbackend.model.survey.SurveyUserVisibilityEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record UpdateSurveyRequest(

        @NotNull(message = "Идентификатор опроса не должен быть пустым")
        Long id,

        @NotBlank(message = "Заголовок не должен быть пустым")
        @Size(max = 50, message = "Длина заголовка не должна превышать 50 символов")
        String title,

        @NotNull(message = "Поле allowChangeAnswer не должно быть пустым")
        Boolean allowChangeAnswer,

        LocalDate startDate,

        LocalDate endDate,

        @NotNull(message = "Поле resultVisibility не должно быть пустым")
        SurveyResultVisibilityEnum resultVisibility,

        @NotNull(message = "Поле surveyStatus не должно быть пустым")
        SurveyStatusEnum surveyStatus,

        @NotNull(message = "Поле userVisibility не должно быть пустым")
        SurveyUserVisibilityEnum userVisibility,

        @NotNull(message = "Список вопросов не должен быть пустым")
        @Size(min = 1, message = "Опрос должен содержать хотя бы один вопрос")
        @Valid  // Валидация для списка вопросов
        List<UpdateQuestionRequest> questions){

}
