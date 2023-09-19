package by.itminsk.cyclingclubbackend.repository;

import by.itminsk.cyclingclubbackend.model.eventdto.EventBlockDTO;
import by.itminsk.cyclingclubbackend.model.user.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    EventBlockDTO findEventById(Long id);
}
