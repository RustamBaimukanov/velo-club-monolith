//package by.itminsk.cyclingclubbackend.controller;
//
//import by.itminsk.cyclingclubbackend.CyclingClubBackendApplication;
//import by.itminsk.cyclingclubbackend.mapper.survey.SurveyMapper;
//import by.itminsk.cyclingclubbackend.model.answer.dto.CreateAnswerRequest;
//import by.itminsk.cyclingclubbackend.model.question.dto.CreateQuestionRequest;
//import by.itminsk.cyclingclubbackend.model.survey.Survey;
//import by.itminsk.cyclingclubbackend.model.survey.VisibilityLevelEnum;
//import by.itminsk.cyclingclubbackend.model.survey.dto.CreateSurveyRequest;
//import by.itminsk.cyclingclubbackend.repository.survey.SurveyRepository;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.util.Assert;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = CyclingClubBackendApplication.class)
//@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application-integration.properties")
//public class SurveyControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private SurveyRepository surveyRepository;
//
//    private SurveyMapper surveyMapper;
//
//    public void surveyResponseToEntityMapping_whenResponseFilled_thenAllPropertiesMappedCorrectly(){
//        List<CreateAnswerRequest> answers = new ArrayList<>();
//        answers.add(new CreateAnswerRequest("Ответ 1"));
//
//        List<CreateQuestionRequest> questions = new ArrayList<>();
//        questions.add(new CreateQuestionRequest("Вопрос 1", answers));
//
//        CreateSurveyRequest survey = new CreateSurveyRequest(true,true,VisibilityLevelEnum.PUBLIC, questions);
//        Survey entity = surveyMapper.createSurveyRequestToSurvey(survey);
//
//        Assert.notEmpty(entity.getQuestions(), "Not Empty");
//        Assert.notEmpty(entity.getQuestions().get(0).getAnswers(), "Not Empty");
//    }
//
//    public void givenSurveys_whenGetSurveys_thenStatus200() throws Exception{
//    }
//}
