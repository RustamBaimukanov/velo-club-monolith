package by.itminsk.cyclingclubbackend.repository.survey_result;

import by.itminsk.cyclingclubbackend.model.survey_result.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
}
