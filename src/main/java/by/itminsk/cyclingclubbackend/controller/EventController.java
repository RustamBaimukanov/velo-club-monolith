package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.Event;
import by.itminsk.cyclingclubbackend.event.EventService;
import by.itminsk.cyclingclubbackend.event.dto.EventCalendarDto;
import by.itminsk.cyclingclubbackend.event.dto.EventCalendarStatusEnum;
import by.itminsk.cyclingclubbackend.event.dto.EventDto;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Tag(name = "Мероприятия", description = "Операции связанные с мероприятиями")
@Slf4j
public class EventController {

    private final EventService eventService;

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
