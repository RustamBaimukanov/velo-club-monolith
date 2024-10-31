package com.work.veloclub.service.survey;

import com.work.veloclub.model.survey.Survey;
import com.work.veloclub.model.survey.SurveyFilter;

import java.util.List;

public interface SurveyService {

    void createSurvey(Survey survey);

    void updateSurvey(Survey survey);

    void removeSurvey(Long id);

    void cancelSurvey(Long id);


    Survey getSurvey(Long id);

    List<Survey> getSurvey(SurveyFilter filter);

}
