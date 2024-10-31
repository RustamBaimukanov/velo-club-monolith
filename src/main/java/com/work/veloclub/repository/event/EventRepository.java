package com.work.veloclub.repository.event;

import com.work.veloclub.model.event_result.EventBlockDTO;
import com.work.veloclub.model.event.Event;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Hidden
public interface EventRepository extends JpaRepository<Event, Long> {

    EventBlockDTO findEventById(Long id);

    List<Event> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime data1, LocalDateTime date2);


}
