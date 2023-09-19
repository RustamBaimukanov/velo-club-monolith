package by.itminsk.cyclingclubbackend.service.event;

import by.itminsk.cyclingclubbackend.model.eventdto.EventBlockDTO;
import by.itminsk.cyclingclubbackend.model.user.EventResult;
import by.itminsk.cyclingclubbackend.repository.EventRepository;
import by.itminsk.cyclingclubbackend.repository.EventResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventResultsRepository eventResultsRepository;

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
