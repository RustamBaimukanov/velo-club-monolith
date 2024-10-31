package com.work.veloclub.repository.answer;

import com.work.veloclub.model.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(nativeQuery = true, value =
            """
            SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM votes v WHERE v.answer_id IN :answerIds
            """)
    Boolean isVotesExistByAnswerIdIn(Set<Long> answerIds);

    @Query(nativeQuery = true, value =
            """
            DELETE FROM votes v WHERE v.answer_id in :answerIds
            """)
    @Transactional
    @Modifying
    void cancelAllVotesByAnswerIdIn(Set<Long> answerIds);
}
