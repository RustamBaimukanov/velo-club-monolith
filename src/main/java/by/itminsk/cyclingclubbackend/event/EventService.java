package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.EventBlockDTO;
import by.itminsk.cyclingclubbackend.event.dto.EventCalendarDto;
import by.itminsk.cyclingclubbackend.event.dto.EventDto;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EventService {

    EventBlockDTO getEvent(Long id);

    List<Event> getEventsByDay(LocalDate date);

    List<Event> getEventsByMonth(LocalDate date);

    List<EventCalendarDto> getEventCalendar(LocalDate date);

    Event getById(Long id);


    void createEvent(@Valid EventPostDto eventPostDto);

    void updateEvent(@Valid EventPostDto eventPostDto);


    void validateCreateEventContent(EventPostDto eventPostDto);

    void eventExistenceValidator(Long id);

}
