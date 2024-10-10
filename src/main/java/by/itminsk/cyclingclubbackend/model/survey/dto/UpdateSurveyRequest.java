package by.itminsk.cyclingclubbackend.model.survey.dto;

import by.itminsk.cyclingclubbackend.model.question.dto.UpdateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.survey.SurveyResultVisibilityEnum;

import java.util.List;

public record UpdateSurveyRequest(Long id,
                                  Boolean allowMultipleAnswer,

                                  Boolean allowChangeAnswer,

                                  SurveyResultVisibilityEnum visibilityLevel,

                                  List<UpdateQuestionRequest> questions) {
}
