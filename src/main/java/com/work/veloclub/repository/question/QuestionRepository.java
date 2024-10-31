package com.work.veloclub.repository.question;

import com.work.veloclub.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("""
            SELECT q from Question q
             WHERE q.id = :id
            
            """)
    Optional<Question> findQuestionById(Long id);
}
