package by.itminsk.cyclingclubbackend.repository.question;

import by.itminsk.cyclingclubbackend.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
