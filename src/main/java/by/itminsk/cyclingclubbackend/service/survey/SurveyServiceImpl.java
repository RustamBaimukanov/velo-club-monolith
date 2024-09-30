package by.itminsk.cyclingclubbackend.service.survey;

import by.itminsk.cyclingclubbackend.model.survey.Survey;
import by.itminsk.cyclingclubbackend.repository.survey.SurveyRepository;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepository surveyRepository;

    @Override
    public void createSurvey(Survey survey) {
        surveyRepository.save(survey);
    }

    @Override
    public void updateSurvey(Survey survey) {
        if (!surveyRepository.existsById(survey.getId())){
            throw new ObjectNotFound("Нельзя редактировать несуществующий опрос");
        }
        surveyRepository.save(survey);
    }

    @Override
    public void removeSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public Survey getSurvey(Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Опрос не найден"));
    }

    @Override
    public List<Survey> getSurvey() {
        return surveyRepository.findAll();
    }
}
