package by.itminsk.cyclingclubbackend.mapper.survey;

import by.itminsk.cyclingclubbackend.model.survey.dto.CreateSurveyRequest;
import by.itminsk.cyclingclubbackend.model.survey.Survey;
import by.itminsk.cyclingclubbackend.model.survey.dto.SurveyResponse;
import by.itminsk.cyclingclubbackend.model.survey.dto.UpdateSurveyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SurveyMapper {

    Survey createSurveyRequestToSurvey(CreateSurveyRequest createSurveyRequest);

    Survey updateSurveyRequestToSurvey(UpdateSurveyRequest createSurveyRequest);

    SurveyResponse surveyToSurveyResponse(Survey survey);

    List<SurveyResponse> surveyToSurveyResponse(List<Survey> survey);


}
