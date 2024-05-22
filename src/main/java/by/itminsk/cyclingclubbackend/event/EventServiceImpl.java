package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.EventBlockDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    private final EventResultsRepository eventResultsRepository;

    @Override
    public EventBlockDTO getEvent(Long id) {
        Map<String, Integer> rating = new HashMap<>();
        for (EventResult result:
        eventResultsRepository.findAllByEventId(id)) {
            rating.put(result.getUser().getFirstName() + " " + result.getUser().getLastName(), result.getPlace());
        }
        EventBlockDTO eventBlockDTO = eventRepository.findEventById(id);
        eventBlockDTO.setRating(rating);
        return eventBlockDTO;
    }

}
