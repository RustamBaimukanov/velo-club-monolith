package com.work.veloclub.repository.event;

import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event.EventGetFilter;
import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.survey.Survey;
import com.work.veloclub.model.survey.SurveyFilter;
import com.work.veloclub.model.user.GenderEnum;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class EventSpecification {

    public static Specification<Event> filterByEventRequest(EventGetFilter filter) {
        return (root, query, criteriaBuilder) -> {
            // Предикаты для хранения условий
            Predicate predicate = criteriaBuilder.conjunction();
            Join<Event, Role> roleJoin = root.join("availableRoles", JoinType.INNER);
            Join<Event, Category> categoryJoin = root.join("category", JoinType.LEFT); // Добавляем join для Category

            // Фильтр по роли
            if (filter.role() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(roleJoin.get("name"), filter.role()));
            }

            // Фильтр по месяцу
            if (filter.month() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(criteriaBuilder.function(
                                "TO_CHAR", String.class, root.get("startDate"), criteriaBuilder.literal("MM")
                        ), String.format("%02d", filter.month())));
            }

            // Фильтр по году
            if (filter.year() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(criteriaBuilder.function(
                                "TO_CHAR", String.class, root.get("startDate"), criteriaBuilder.literal("YYYY")
                        ), filter.year().toString()));
            }

            // Фильтр по ID города
            if (filter.cityId() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("city").get("id"), filter.cityId()));
            }

            // Фильтр по полу
            if (filter.gender() != null && filter.gender() != GenderEnum.ANY) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("availableGender"), filter.gender()));
            }

            // Фильтр по категориям
            if (filter.categories() != null && !filter.categories().isEmpty() && (filter.role() == null || filter.role() != RoleEnum.DABBLER)) {
                predicate = criteriaBuilder.and(predicate,
                        categoryJoin.get("id").in(filter.categories()));
            }

            return predicate;
        };
    }
}