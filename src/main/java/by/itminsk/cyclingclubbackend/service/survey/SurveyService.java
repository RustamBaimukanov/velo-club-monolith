package by.itminsk.cyclingclubbackend.service.survey;

import by.itminsk.cyclingclubbackend.model.survey.Survey;

import java.util.List;

public interface SurveyService {

    void createSurvey(Survey survey);

    void updateSurvey(Survey survey);

    void removeSurvey(Long id);

    Survey getSurvey(Long id);

    List<Survey> getSurvey();

}
