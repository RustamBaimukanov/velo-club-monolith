package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.CyclingClubBackendApplication;
import by.itminsk.cyclingclubbackend.repository.survey.SurveyRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CyclingClubBackendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration.properties")
public class SurveyControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SurveyRepository surveyRepository;

    public void givenSurveys_whenGetSurveys_thenStatus200() throws Exception{
    }
}
