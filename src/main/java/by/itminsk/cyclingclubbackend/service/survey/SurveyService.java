package by.itminsk.cyclingclubbackend.service.survey;

import by.itminsk.cyclingclubbackend.model.survey.Survey;

public interface SurveyService {

    void createSurvey(Survey survey);

    void updateSurvey(Survey survey);

    void removeSurvey(Long id);

}
