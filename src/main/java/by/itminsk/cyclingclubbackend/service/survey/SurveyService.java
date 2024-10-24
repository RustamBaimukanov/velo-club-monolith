package by.itminsk.cyclingclubbackend.service.survey;

import by.itminsk.cyclingclubbackend.model.survey.Survey;
import by.itminsk.cyclingclubbackend.model.survey.SurveyFilter;

import java.util.List;

public interface SurveyService {

    void createSurvey(Survey survey);

    void updateSurvey(Survey survey);

    void removeSurvey(Long id);

    void cancelSurvey(Long id);


    Survey getSurvey(Long id);

    List<Survey> getSurvey(SurveyFilter filter);

}
