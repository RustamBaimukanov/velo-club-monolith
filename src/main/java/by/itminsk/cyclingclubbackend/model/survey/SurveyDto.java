package by.itminsk.cyclingclubbackend.model.survey;

import by.itminsk.cyclingclubbackend.model.question.QuestionDto;

import java.util.List;

public record SurveyDto(Long id,

                        Boolean allowMultipleAnswer,

                        Boolean allowChangeAnswer,

                        VisibilityLevelEnum visibilityLevel,

                        List<QuestionDto> questions
) {


}
