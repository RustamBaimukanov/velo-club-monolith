package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.EventBlockDTO;
import by.itminsk.cyclingclubbackend.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    EventBlockDTO findEventById(Long id);
}