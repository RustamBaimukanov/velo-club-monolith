package com.work.veloclub.repository.survey;

import com.work.veloclub.model.survey.Survey;
import com.work.veloclub.model.survey.SurveyFilter;
import com.work.veloclub.model.survey.Survey_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class SurveySpecification {

    public static Specification<Survey> filterBySurvey(SurveyFilter filter) {
        return (root, query, criteriaBuilder) -> {
            // Предикаты для хранения условий
            Predicate predicate = criteriaBuilder.conjunction();

            // Фильтр по title
            if (filter.title() != null && !filter.title().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Survey_.title)), "%" + filter.title().toLowerCase() + "%"));
            }

            // Фильтр по allowChangeAnswer
            if (filter.allowChangeAnswer() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(Survey_.allowChangeAnswer), filter.allowChangeAnswer()));
            }

            // Фильтр по startDate и endDate
            if (filter.startDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get(Survey_.startDate), filter.startDate()));
            }

            if (filter.endDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get(Survey_.endDate), filter.endDate()));
            }

            // Фильтр по resultVisibility
            if (filter.resultVisibility() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(Survey_.resultVisibility), filter.resultVisibility()));
            }

            // Фильтр по surveyStatus
            if (filter.surveyStatus() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(Survey_.surveyStatus), filter.surveyStatus()));
            }

            // Фильтр по userVisibility
            if (filter.userVisibility() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(Survey_.userVisibility), filter.userVisibility()));
            }

            return predicate;
        };
    }
}
