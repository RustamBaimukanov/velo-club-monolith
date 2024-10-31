package com.work.veloclub.repository.survey;

import com.work.veloclub.model.survey.Survey;
import com.work.veloclub.model.survey.SurveyResultVisibilityEnum;
import com.work.veloclub.model.survey.SurveyStatusEnum;
import com.work.veloclub.model.survey.SurveyUserVisibilityEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")  // Используем профиль для тестирования
@Transactional
public class SurveyRepositoryTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @PersistenceContext
    private EntityManager entityManager;  // Используется для сохранения тестовых данных

    @BeforeEach
    public void setUp() {

        // Создаем и сохраняем тестовые опросы
        Survey survey1 = Survey.builder()
                .title("Survey 1")
                .allowChangeAnswer(true)
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(1))
                .resultVisibility(SurveyResultVisibilityEnum.PUBLIC)
                .surveyStatus(SurveyStatusEnum.ACTIVE)
                .userVisibility(SurveyUserVisibilityEnum.ANONYMOUS)
                .questions(Arrays.asList()) // или добавьте тестовые вопросы
                .build();

        Survey survey2 = Survey.builder()
                .title("Survey 2")
                .allowChangeAnswer(false)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(2))
                .resultVisibility(SurveyResultVisibilityEnum.PRIVATE)
                .surveyStatus(SurveyStatusEnum.INACTIVE)
                .userVisibility(SurveyUserVisibilityEnum.IDENTIFIED)
                .questions(Arrays.asList()) // или добавьте тестовые вопросы
                .build();

        entityManager.persist(survey1);
        entityManager.persist(survey2);
    }

    @Test
    public void whenFindSurveyById_thenReturnSurvey() {
        // Поиск опроса по ID
        Optional<Survey> foundSurvey = surveyRepository.findSurveyById(1L); // Предполагаем, что ID = 1 существует

        assertTrue(foundSurvey.isPresent());
        assertEquals("Survey 1", foundSurvey.get().getTitle());
    }

    @Test
    public void whenFindSurveyByIdNotFound_thenReturnEmpty() {
        // Проверяем, что поиск по несуществующему ID возвращает пустой результат
        Optional<Survey> foundSurvey = surveyRepository.findSurveyById(999L); // ID, которого нет
        assertFalse(foundSurvey.isPresent());
    }

    @Test
    public void whenFindAllSurveysWithSpecification_thenReturnSurveys() {
        // Пример спецификации для поиска опросов с названием "Survey 1"
        Specification<Survey> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("title"), "Survey 1");

        // Получаем все опросы по спецификации
        List<Survey> surveys = surveyRepository.findAll(spec);

        // Проверяем, что мы нашли именно тот опрос, который искали
        assertEquals(1, surveys.size());
        assertEquals("Survey 1", surveys.get(0).getTitle());
    }

    @Test
    public void whenFindAllSurveys_thenReturnAllSurveys() {
        // Проверяем, что все опросы были успешно сохранены
        List<Survey> surveys = surveyRepository.findAll();

        assertEquals(2, surveys.size()); // У нас должно быть 3 опроса
    }
}