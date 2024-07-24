package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.EventBlockDTO;
import by.itminsk.cyclingclubbackend.event.dto.EventDto;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

public interface EventService {

    EventBlockDTO getEvent(Long id);

    List<EventDto> getEventsByDay(Date date);

    void createEvent(@Valid EventPostDto eventPostDto);

    void validateCreateEventContent(EventPostDto eventPostDto);
}
