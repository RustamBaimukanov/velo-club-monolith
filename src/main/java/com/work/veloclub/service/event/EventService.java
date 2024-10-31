package com.work.veloclub.service.event;

import com.work.veloclub.model.event_result.EventBlockDTO;
import com.work.veloclub.model.event.EventCalendarDto;
import com.work.veloclub.model.event.EventPostDto;
import com.work.veloclub.model.event.Event;
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
