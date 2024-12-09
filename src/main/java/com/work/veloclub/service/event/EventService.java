package com.work.veloclub.service.event;

import com.work.veloclub.model.event.EventCreateDTO;
import com.work.veloclub.model.event.EventCalendarDto;
import com.work.veloclub.model.event.Event;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    Event createEvent(EventCreateDTO eventPostDto);

    List<Event> createEventGenerate(List<EventCreateDTO> eventPostDto);


    Event updateEvent(Long id, EventCreateDTO eventPostDto);


//    EventBlockDTO getEvent(Long id);

    List<Event> getEventsByDay(LocalDate date);

    List<Event> getEventsByMonth(LocalDate date);

    List<EventCalendarDto> getEventCalendar(LocalDate date);

    Event getById(Long id);





    void validateCreateEventContent(EventCreateDTO eventPostDto);

    void eventExistenceValidator(Long id);

}
