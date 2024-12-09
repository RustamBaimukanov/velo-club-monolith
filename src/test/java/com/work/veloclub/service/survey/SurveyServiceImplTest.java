//package com.work.veloclub.service.survey;
//
//import com.work.veloclub.model.survey.Survey;
//import com.work.veloclub.repository.answer.AnswerRepository;
//import com.work.veloclub.repository.survey.SurveyRepository;
//import com.work.veloclub.util.exception_handler.ObjectNotFound;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class SurveyServiceImplTest {
//
//    @Mock
//    private SurveyRepository surveyRepository;
//
//    @Mock
//    private AnswerRepository answerRepository;
//
//    @InjectMocks
//    private SurveyServiceImpl surveyService;
//
//    @Test
//    void whenCreateSurvey_thenSurveyIsSaved() {
//        Survey survey = new Survey(); // создайте объект Survey
//        surveyService.createSurvey(survey);
//
//        // Проверяем, что метод save() был вызван с объектом survey
//        verify(surveyRepository, times(1)).save(survey);
//    }
//
//    @Test
//    public void whenUpdateSurvey_thenSurveyIsUpdated() {
//        Survey survey = new Survey();
//        survey.setId(1L);
//
//        when(surveyRepository.existsById(survey.getId())).thenReturn(true);
//        when(answerRepository.isVotesExistByAnswerIdIn(anySet())).thenReturn(false);
//
//        surveyService.updateSurvey(survey);
//
//        // Проверяем, что опрос существует и обновляется
//        verify(surveyRepository, times(1)).existsById(survey.getId());
//        verify(answerRepository, times(1)).isVotesExistByAnswerIdIn(anySet());
//    }
//
//    @Test
//    public void whenUpdateSurvey_andSurveyNotFound_thenThrowException() {
//        Survey survey = new Survey();
//        survey.setId(1L);
//
//        when(surveyRepository.existsById(survey.getId())).thenReturn(false);
//
//        assertThrows(ObjectNotFound.class, () -> surveyService.updateSurvey(survey));
//
//        // Проверяем, что проверка существования опроса была вызвана
//        verify(surveyRepository, times(1)).existsById(survey.getId());
//    }
//
//    @Test
//    public void whenRemoveSurvey_thenSurveyIsDeleted() {
//        Long surveyId = 1L;
//
//        surveyService.removeSurvey(surveyId);
//
//        // Проверяем, что метод deleteById() был вызван с правильным ID
//        verify(surveyRepository, times(1)).deleteById(surveyId);
//    }
//
//    @Test
//    public void whenCancelSurvey_thenVotesAreCanceled() {
//        Long surveyId = 1L;
//        Survey survey = new Survey();
//        survey.setQuestions(Collections.emptyList()); // Предположим, пустой набор вопросов
//
//        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));
//
//        surveyService.cancelSurvey(surveyId);
//
//        // Проверяем, что метод cancelAllVotesByAnswerIdIn() был вызван
//        verify(answerRepository, times(1)).cancelAllVotesByAnswerIdIn(anySet());
//    }
//
//    @Test
//    public void whenGetSurvey_thenSurveyIsReturned() {
//        Long surveyId = 1L;
//        Survey survey = new Survey();
//        survey.setId(surveyId);
//
//        when(surveyRepository.findSurveyById(surveyId)).thenReturn(Optional.of(survey));
//
//        Survey foundSurvey = surveyService.getSurvey(surveyId);
//
//        // Проверяем, что вернулся правильный опрос
//        assertEquals(survey, foundSurvey);
//    }
//
//    @Test
//    public void whenGetSurvey_andSurveyNotFound_thenThrowException() {
//        Long surveyId = 1L;
//
//        when(surveyRepository.findSurveyById(surveyId)).thenReturn(Optional.empty());
//
//        assertThrows(ObjectNotFound.class, () -> surveyService.getSurvey(surveyId));
//    }
//}