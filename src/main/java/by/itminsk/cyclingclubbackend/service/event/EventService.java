package by.itminsk.cyclingclubbackend.service.event;

import by.itminsk.cyclingclubbackend.model.event_result.EventBlockDTO;
import by.itminsk.cyclingclubbackend.model.event.EventCalendarDto;
import by.itminsk.cyclingclubbackend.model.event.EventPostDto;
import by.itminsk.cyclingclubbackend.model.event.Event;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    EventBlockDTO getEvent(Long id);

    List<Event> getEventsByDay(LocalDate date);

    List<Event> getEventsByMonth(LocalDate date);

    List<EventCalendarDto> getEventCalendar(LocalDate date);

    Event getById(Long id);


    void createEvent(@Valid Event event);

    void updateEvent(@Valid EventPostDto eventPostDto);


    void validateCreateEventContent(EventPostDto eventPostDto);

    void eventExistenceValidator(Long id);

}
