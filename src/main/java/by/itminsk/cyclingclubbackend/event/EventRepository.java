package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.EventBlockDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Hidden
public interface EventRepository extends JpaRepository<Event, Long> {

    EventBlockDTO findEventById(Long id);

    List<Event> findAllByStartDateAfterAndEndDateBefore(Date data1, Date date2);
}
