package by.itminsk.cyclingclubbackend.repository.survey;

import by.itminsk.cyclingclubbackend.model.survey.Survey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
//    @EntityGraph(attributePaths = {"questions", "questions.answers", "questions.answers.users", "questions.answers.users.role"})
    @Query("select s from Survey s where s.id = :id")
    Optional<Survey> findSurveyById(Long id);
}
