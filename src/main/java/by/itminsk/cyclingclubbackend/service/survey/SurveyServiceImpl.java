package by.itminsk.cyclingclubbackend.service.survey;

import by.itminsk.cyclingclubbackend.model.answer.Answer;
import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.survey.Survey;
import by.itminsk.cyclingclubbackend.model.survey.SurveyFilter;
import by.itminsk.cyclingclubbackend.model.survey.SurveyResultVisibilityEnum;
import by.itminsk.cyclingclubbackend.repository.answer.AnswerRepository;
import by.itminsk.cyclingclubbackend.repository.survey.SurveyRepository;
import by.itminsk.cyclingclubbackend.repository.survey.SurveySpecification;
import by.itminsk.cyclingclubbackend.repository.user.UserRepository;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepository surveyRepository;

    private final AnswerRepository answerRepository;

    @Override
    public void createSurvey(Survey survey) {
        surveyRepository.save(survey);
    }

    @Override
    @Transactional
    public void updateSurvey(Survey survey) {
        log.info("Updating survey with ID: {}", survey.getId());
        if (!surveyRepository.existsById(survey.getId())) {
            throw new ObjectNotFound("Опрос не найден.");
        }
        Set<Long> answerIds = survey.getQuestions().stream()
                .map(Question::getAnswers)
                .flatMap(Collection::stream)
                .map(Answer::getId)
                .collect(Collectors.toSet());

        if (answerRepository.isVotesExistByAnswerIdIn(answerIds)) {
            throw new ObjectNotFound("Нельзя редактировать проходящий опрос.");
        }
        //surveyRepository.save(survey);
    }

    @Override
    public void removeSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public void cancelSurvey(Long id) {
        Set<Long> answerIds = surveyRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Опрос не найден.")).getQuestions().stream().map(Question::getAnswers).flatMap(Collection::stream).map(Answer::getId).collect(Collectors.toSet());
        answerRepository.cancelAllVotesByAnswerIdIn(answerIds);
    }

    @Override
//    @Transactional(readOnly = true)
    public Survey getSurvey(Long id) {
        return surveyRepository.findSurveyById(id).orElseThrow(() -> new ObjectNotFound("Опрос не найден"));
    }

    @Override
    public List<Survey> getSurvey(SurveyFilter filter) {

        return surveyRepository.findAll(SurveySpecification.filterBySurvey(filter));
    }
}
