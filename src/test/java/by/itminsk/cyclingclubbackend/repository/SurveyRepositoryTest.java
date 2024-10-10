//package by.itminsk.cyclingclubbackend.repository;
//
//import by.itminsk.cyclingclubbackend.model.answer.Answer;
//import by.itminsk.cyclingclubbackend.model.question.Question;
//import by.itminsk.cyclingclubbackend.model.survey.Survey;
//import by.itminsk.cyclingclubbackend.model.survey.SurveyResultVisibilityEnum;
//import by.itminsk.cyclingclubbackend.model.survey.SurveyStatusEnum;
//import by.itminsk.cyclingclubbackend.model.survey.SurveyUserVisibilityEnum;
//import by.itminsk.cyclingclubbackend.model.user.GenderEnum;
//import by.itminsk.cyclingclubbackend.model.user.User;
//import by.itminsk.cyclingclubbackend.repository.survey.SurveyRepository;
//import by.itminsk.cyclingclubbackend.repository.user.UserRepository;
//import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Commit;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashSet;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(
//        locations = "classpath:application-integration.properties")
////@ContextConfiguration(classes = {
////        SurveyMapperImpl.class
////})
//@Sql(scripts = "classpath:data.sql")
//@Slf4j
//public class SurveyRepositoryTest {
//
//    @Autowired
//    private SurveyRepository surveyRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @BeforeEach
////    public void setUpSurvey(){
////    }
//
////    @AfterAll
////    public void clean() {
////        // Release test data after each test method
////        surveyRepository.deleteAll();
////    }
//
//    @Test
//    void givenSurvey_whenPersist_thenPersistCorrectlyItself() {
//        LocalDate currentDate = LocalDate.now();
//        LocalDate startDate = LocalDate.of(2024, 7, 6);
//        LocalDate endDate = LocalDate.of(2024, 8, 11);
//
//        Survey survey = Survey.builder()
//                .title("Test survey")
//                .allowChangeAnswer(true)
//                .createdAt(currentDate)
//                .startDate(startDate)
//                .endDate(endDate)
//                .resultVisibility(SurveyResultVisibilityEnum.PUBLIC)
//                .surveyStatus(
//                        setSurveyStatusAroundDates(currentDate, startDate, endDate)
//                )
//                .userVisibility(SurveyUserVisibilityEnum.IDENTIFIED)
//                .build();
//        surveyRepository.save(survey);
//
//        Survey createdSurvey = surveyRepository.findById(survey.getId())
//                .orElseThrow(() -> new ObjectNotFound("Survey does not exist"));
//        Assert.notNull(createdSurvey, "Survey is null");
//        Assert.isTrue(survey.getQuestions().isEmpty(), "Survey has unexpected questions");
//        log.info("SURVEY ITSELF");
//        log.info("ID: {}", createdSurvey.getId());
//        log.info("Title: {}", createdSurvey.getTitle());
//        log.info("Created at: {}", createdSurvey.getCreatedAt());
//        log.info("Start date: {}", createdSurvey.getStartDate());
//        log.info("End date: {}", createdSurvey.getEndDate());
//        log.info("Allow change answer: {}" , createdSurvey.getAllowChangeAnswer());
//        log.info("Survey status: {}", createdSurvey.getSurveyStatus());
//        log.info("----------------------------");
//    }
//
//    @Test
//    void givenSurveyWithQuestion_whenPersist_thenPersistCorrectly() {
//        LocalDate currentDate = LocalDate.now();
//        LocalDate startDate = LocalDate.of(2024, 7, 6);
//        LocalDate endDate = LocalDate.of(2024, 8, 11);
//
//        Survey survey = Survey.builder()
//                .title("Test survey")
//                .allowChangeAnswer(true)
//                .createdAt(currentDate)
//                .startDate(startDate)
//                .endDate(endDate)
//                .resultVisibility(SurveyResultVisibilityEnum.PUBLIC)
//                .surveyStatus(
//                        setSurveyStatusAroundDates(currentDate, startDate, endDate)
//                )
//                .userVisibility(SurveyUserVisibilityEnum.IDENTIFIED)
//                .build();
//
//        Question question = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 1")
//                .build();
//
//        Question question2 = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 2")
//                .build();
//
//        Question question3 = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 3")
//                .build();
//
//        survey.addQuestion(question);
//        survey.addQuestion(question2);
//        survey.addQuestion(question3);
//
//        surveyRepository.save(survey);
//
//        Survey createdSurvey = surveyRepository.findById(survey.getId())
//                .orElseThrow(() -> new ObjectNotFound("Survey does not exist"));
//        Assert.notNull(createdSurvey, "Survey is null");
//        Assert.notNull(survey.getQuestions(), "Survey does not have related question");
//        log.info("SURVEY WITH QUESTION");
//        log.info("ID: {}", createdSurvey.getId());
//        log.info("Title: {}", createdSurvey.getTitle());
//        log.info("Created at: {}", createdSurvey.getCreatedAt());
//        log.info("Start date: {}", createdSurvey.getStartDate());
//        log.info("End date: {}", createdSurvey.getEndDate());
//        log.info("Allow change answer: {}" , createdSurvey.getAllowChangeAnswer());
//        log.info("Survey status: {}", createdSurvey.getSurveyStatus());
//        log.info("----------------------------");
//        log.info("Questions");
//        for (Question createdQuestion:
//             createdSurvey.getQuestions()) {
//            log.info("ID: {}", createdQuestion.getId());
//            log.info("QUESTION: {}", createdQuestion.getQuestion());
//            log.info("Allow multiple answer: {}" , createdQuestion.getAllowMultipleAnswer());
//            log.info("Requires user participation: {}" , createdQuestion.getIsRequired());
//            log.info("----------------------------");
//        }
//    }
//
//    @Test
//    void givenSurveyWithQuestionAndAnswersForQuestion_whenPersist_thenPersistCorrectly() {
//        LocalDate currentDate = LocalDate.now();
//        LocalDate startDate = LocalDate.of(2024, 7, 6);
//        LocalDate endDate = LocalDate.of(2024, 8, 11);
//
//        Survey survey = Survey.builder()
//                .title("Test survey")
//                .allowChangeAnswer(true)
//                .createdAt(currentDate)
//                .startDate(startDate)
//                .endDate(endDate)
//                .resultVisibility(SurveyResultVisibilityEnum.PUBLIC)
//                .surveyStatus(
//                        setSurveyStatusAroundDates(currentDate, startDate, endDate)
//                )
//                .userVisibility(SurveyUserVisibilityEnum.IDENTIFIED)
//                .build();
//
//        Question question = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 1")
//                .build();
//
//        Answer answer = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 1")
//                .build();
//
//        Answer answer2 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 2")
//                .build();
//
//        Answer answer3 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 3")
//                .build();
//
//        question.addAnswer(answer);
//        question.addAnswer(answer2);
//        question.addAnswer(answer3);
//
//        Question question2 = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 2")
//                .build();
//
//
//        Answer answer4 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 1")
//                .build();
//
//        Answer answer5 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 2")
//                .build();
//
//        Answer answer6 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 3")
//                .build();
//
//        question2.addAnswer(answer4);
//        question2.addAnswer(answer5);
//        question2.addAnswer(answer6);
//
//        Question question3 = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 3")
//                .build();
//
//        Answer answer7 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 1")
//                .build();
//
//        Answer answer8 = Answer.builder()
//                .ownOption(true)
//                .answer("Test answer 2 placeholder")
//                .build();
//
//        Answer answer9 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 3")
//                .build();
//
//        question2.addAnswer(answer7);
//        question2.addAnswer(answer8);
//        question2.addAnswer(answer9);
//
//        survey.addQuestion(question);
//        survey.addQuestion(question2);
//        survey.addQuestion(question3);
//
//        surveyRepository.save(survey);
//
//        Survey createdSurvey = surveyRepository.findById(survey.getId())
//                .orElseThrow(() -> new ObjectNotFound("Survey does not exist"));
//        Assert.notNull(createdSurvey, "Survey is null");
//        Assert.notNull(survey.getQuestions(), "Survey does not have related question");
//        log.info("SURVEY WITH QUESTION");
//        log.info("ID: {}", createdSurvey.getId());
//        log.info("Title: {}", createdSurvey.getTitle());
//        log.info("Created at: {}", createdSurvey.getCreatedAt());
//        log.info("Start date: {}", createdSurvey.getStartDate());
//        log.info("End date: {}", createdSurvey.getEndDate());
//        log.info("Allow change answer: {}" , createdSurvey.getAllowChangeAnswer());
//        log.info("Survey status: {}", createdSurvey.getSurveyStatus());
//        log.info("----------------------------");
//        log.info("Questions");
//        for (Question createdQuestion:
//                createdSurvey.getQuestions()) {
//            log.info("ID: {}", createdQuestion.getId());
//            log.info("QUESTION: {}", createdQuestion.getQuestion());
//            log.info("Allow multiple answer: {}" , createdQuestion.getAllowMultipleAnswer());
//            log.info("Requires user participation: {}" , createdQuestion.getIsRequired());
//            log.info("----------------------------");
//            log.info("Answers");
//            for (Answer createdAnswer:
//                    createdQuestion.getAnswers()) {
//                log.info("ID: {}", createdAnswer.getId());
//                log.info("ANSWER: {}", createdAnswer.getAnswer());
//                log.info("Is own answer: {}" , createdAnswer.getOwnOption());
//                log.info("----------------------------");
//            }
//        }
//    }
//
//    private SurveyStatusEnum setSurveyStatusAroundDates(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
//        if (startDate == null && endDate == null) {
//            return SurveyStatusEnum.ACTIVE;
//        } else if (startDate == null
//                && (currentDate.isBefore(endDate) || currentDate.equals(endDate))) {
//            return SurveyStatusEnum.ACTIVE;
//        } else if (endDate == null
//                && (currentDate.isAfter(startDate) || currentDate.equals(startDate))) {
//            return SurveyStatusEnum.ACTIVE;
//        } else if ((currentDate.isAfter(startDate) || currentDate.equals(startDate))
//                &&
//                currentDate.isBefore(endDate) || currentDate.equals(endDate)) {
//            return SurveyStatusEnum.ACTIVE;
//        } else
//            return SurveyStatusEnum.INACTIVE;
//    }
//
//    @BeforeEach
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    void collectSurveyQuestionsAnswers() {
//        LocalDate currentDate = LocalDate.now();
//        LocalDate startDate = LocalDate.of(2024, 7, 6);
//        LocalDate endDate = LocalDate.of(2024, 8, 11);
//
//
//        Survey survey = Survey.builder()
//                .title("Test survey")
//                .allowChangeAnswer(true)
//                .createdAt(currentDate)
//                .startDate(startDate)
//                .endDate(endDate)
//                .resultVisibility(SurveyResultVisibilityEnum.PUBLIC)
//                .surveyStatus(
//                        setSurveyStatusAroundDates(currentDate, startDate, endDate)
//                )
//                .userVisibility(SurveyUserVisibilityEnum.IDENTIFIED)
//                .build();
//
//        Question question = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 1")
//                .build();
//
//        Answer answer = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 1")
//                .users(new HashSet<>())
//                .build();
//
//        answer.addUser(userRepository.findById(1L).orElseThrow(() -> new ObjectNotFound("FE")));
//
//        Answer answer2 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 2")
//                .build();
//
//        Answer answer3 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 3")
//                .build();
//
//        question.addAnswer(answer);
//        question.addAnswer(answer2);
//        question.addAnswer(answer3);
//
//        Question question2 = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 2")
//                .build();
//
//
//        Answer answer4 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 1")
//                .build();
//
//        Answer answer5 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 2")
//                .build();
//
//        Answer answer6 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 3")
//                .build();
//
//        question2.addAnswer(answer4);
//        question2.addAnswer(answer5);
//        question2.addAnswer(answer6);
//
//        Question question3 = Question.builder()
//                .allowMultipleAnswer(false)
//                .isRequired(false)
//                .question("Test question 3")
//                .build();
//
//        Answer answer7 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 1")
//                .build();
//
//        Answer answer8 = Answer.builder()
//                .ownOption(true)
//                .answer("Test answer 2 placeholder")
//                .build();
//
//        Answer answer9 = Answer.builder()
//                .ownOption(false)
//                .answer("Test answer 3")
//                .build();
//
//        question2.addAnswer(answer7);
//        question2.addAnswer(answer8);
//        question2.addAnswer(answer9);
//
//        survey.addQuestion(question);
//        survey.addQuestion(question2);
//        survey.addQuestion(question3);
//
//        surveyRepository.save(survey);
//
//    }
//
//    @Test
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    void givenSurvey_whenSaved_thenCanBeFoundById() {
//        log.info("START");
//        Survey survey = surveyRepository.findById(1L).orElseThrow(() -> new ObjectNotFound("NO"));
//        log.info("END");
//        Assert.notNull(survey, "Survey");
//        Assert.notNull(survey.getId(), "Survey ID");
//
////        Survey savedSurvey = surveyRepository.findById(survey.getId()).orElse(null);
////        Assert.notNull(savedSurvey.getQuestions());
//
//    }
//}
