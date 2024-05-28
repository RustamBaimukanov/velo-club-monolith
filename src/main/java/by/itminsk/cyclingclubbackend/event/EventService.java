package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.EventBlockDTO;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import jakarta.validation.Valid;

public interface EventService {

    EventBlockDTO getEvent(Long id);

    void createEvent(@Valid EventPostDto eventPostDto);

    void validateCreateEventContent(EventPostDto eventPostDto);
}
