package com.work.veloclub.repository.event_result;

import com.work.veloclub.model.event_result.EventResult;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventResultsRepository extends JpaRepository<EventResult, Long> {

    @Query(value = """
            select er from EventResult er
            left join fetch er.userProfile up
            left join fetch up.city
            left join fetch up.city
            where er.event.id = :eventId
            """)
    List<EventResult> findAllByEventId(@Param("eventId") Long eventId);
}