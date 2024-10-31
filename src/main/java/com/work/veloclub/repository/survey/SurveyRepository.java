package com.work.veloclub.repository.survey;

import com.work.veloclub.model.survey.Survey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {
    @Query("select s from Survey s where s.id = :id")
    Optional<Survey> findSurveyById(Long id);

    List<Survey> findAll(Specification<Survey> spec);
}
