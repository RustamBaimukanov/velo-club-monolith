package com.work.veloclub.repository.event;


import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {


//    EventBlockDTO findEventById(Long id);

    List<Event> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime data1, LocalDateTime date2);


}
