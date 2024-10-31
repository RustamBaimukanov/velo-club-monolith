package com.work.veloclub.controller;

import com.work.veloclub.mapper.event.EventMapper;
import com.work.veloclub.model.event.Event;
import com.work.veloclub.service.event.EventService;
import com.work.veloclub.model.event.EventCalendarDto;
import com.work.veloclub.model.event.EventDto;
import com.work.veloclub.model.event.EventPostDto;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Tag(name = "Мероприятия", description = "Операции связанные с мероприятиями")
@Slf4j
public class EventController {

    private final EventService eventService;

    private final EventMapper eventMapper;

    @Operation(
            summary = "Добавление мероприятия",
            description = "API добавления мероприятия(будет дополнено, после обсуждении)."
    )
    @PostMapping(value = "/event")
    public ResponseEntity<?> addEvent(@RequestBody EventPostDto eventDto) {
        eventService.validateCreateEventContent(eventDto);
        Event event = eventMapper.eventDtoToEvent(eventDto);
        eventService.createEvent(event);
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Редактирование существующего мероприятия",
            description = "API редактирования мероприятия(будет дополнено, после обсуждении)."
    )
    @PutMapping(value = "/event")
    public ResponseEntity<?> updateEvent(@RequestBody EventPostDto event) {
        eventService.validateCreateEventContent(event);
        eventService.eventExistenceValidator(event.getId());
        eventService.updateEvent(event);
        return ResponseEntity.ok("");
    }

    @GetMapping("/event/{id}")
    public String getEvent(@PathVariable Long id) {
        return new Gson().toJson(eventService.getEvent(id));
    }

    @Operation(
            summary = "Получение мероприятии по текущей дате",
            description = ""
    )
    @GetMapping("/event")
    public List<EventDto> getEventByCalendar(@RequestParam
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                             LocalDate date) {
        return eventService.getEventsByDay(date).stream().map(event -> EventDto.builder()
                .eventName(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .participantsCategory(event.getCategory())
                .build()).collect(Collectors.toList());
    }

    @Operation(
            summary = "Получение календаря мероприятии по дате",
            description = ""
    )
    @GetMapping("/event/calendar")
    public List<EventCalendarDto> getCalendar(@RequestParam
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                              LocalDate date) {




        return eventService.getEventCalendar(date);
    }


}
