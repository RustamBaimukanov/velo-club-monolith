package com.work.veloclub.service.event;

import com.work.veloclub.model.event.EventCreateDTO;
import com.work.veloclub.model.event.EventCalendarDto;
import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event.EventGetFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    List<Event> getEventsByFilter(EventGetFilter filter);

    List<Event> getEventsByUserIdAndYear(Long id, Integer year);

    List<Event> getEventsByUserIdAndYear(Long id, Integer year, Integer page, Integer size);



    Event getById(Long id);





    void validateCreateEventContent(EventCreateDTO eventPostDto);

    void eventExistenceValidator(Long id);

    void script();
}
