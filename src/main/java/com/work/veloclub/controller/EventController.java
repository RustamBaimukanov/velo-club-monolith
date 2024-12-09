package com.work.veloclub.controller;


import com.work.veloclub.mapper.event.EventMapper;
import com.work.veloclub.mapper.event_result.EventResultMapper;
import com.work.veloclub.mapper.race.RaceMapper;
import com.work.veloclub.model.event.*;
import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.model.event_result.EventResultCreateRequest;
import com.work.veloclub.model.event_result.EventResultUpdateRequest;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.service.event.EventService;
import com.work.veloclub.service.event_result.EventResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/events")
@RequiredArgsConstructor
@Tag(name = "Мероприятия", description = "Операции связанные с мероприятиями")
@Slf4j
public class EventController {

    private final EventService eventService;

    private final EventResultService eventResultService;

    @Operation(
            summary = "Добавление мероприятия",
            description = "API добавления мероприятия(будет дополнено, после обсуждении)."
    )
    @PostMapping
    public ResponseEntity<?> addEvent(@RequestBody @Valid EventCreateDTO eventDto) {
        //eventService.validateCreateEventContent(eventDto);
        //TODO исправить
        Event event = eventService.createEvent(eventDto);
        //TODO вернуть ивент после создания
        return ResponseEntity.ok(EventMapper.mapToEventDto(event));
    }

    @Operation(
            summary = "Добавление мероприятия(генерация данных, не использовать, будет удалено до релиза)",
            description = "API добавления мероприятия(будет дополнено, после обсуждении)."
    )
    @PostMapping("/generate")
    public ResponseEntity<?> addEventGenerate(@RequestBody List<EventCreateDTO> eventDto) {
        //eventService.validateCreateEventContent(eventDto);
        //TODO исправить
        List<Event> events = eventService.createEventGenerate(eventDto);
        //TODO вернуть ивент после создания
        return ResponseEntity.ok(EventMapper.mapToEventDto(events));
    }
    @Operation(
            summary = "Редактирование существующего мероприятия",
            description = "API редактирования мероприятия(будет дополнено, после обсуждении)."
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody @Valid EventCreateDTO eventDto) {
//        eventService.validateCreateEventContent(event);
//        eventService.eventExistenceValidator(event.getId());
        Event event = eventService.updateEvent(id, eventDto);
        //TODO вернуть ивент после создания
        return ResponseEntity.ok(EventMapper.mapToEventDto(event));
    }

    @Operation(
            summary = "Получение мероприятии по текущей дате",
            description = ""
    )
    @GetMapping
    public ResponseEntity<?> getEventByCalendar(@RequestParam
                                             LocalDate date) {
        Function<Set<RoleEnum>, RolesEnum> convert = x -> x.contains(RoleEnum.SPORTSMAN)
                && x.contains(RoleEnum.DABBLER)
                ? RolesEnum.ANY
                : x.contains(RoleEnum.SPORTSMAN)
                ? RolesEnum.SPORTSMAN
                : RolesEnum.DABBLER;
        return ResponseEntity.ok(eventService.getEventsByDay(date).stream().map(event -> EventCalendarResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .participantsCategory(convert.apply(event.getAvailableRoles().stream().map(Role::getName).collect(Collectors.toSet())))
                .race(RaceMapper.mapToRaceResponse(event.getRace()))
                .build()).collect(Collectors.toList()));
    }

    @Operation(
            summary = "Получение календаря мероприятии по дате",
            description = ""
    )
    @GetMapping("/calendar")
    public ResponseEntity<?> getCalendar(@RequestParam
                                              LocalDate date) {
        return ResponseEntity.ok(eventService.getEventCalendar(date));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Создание результатов администратором")
    @PostMapping("/{id}/results")
    public ResponseEntity<?> addEventResult(@PathVariable Long id, @RequestBody @Valid EventResultCreateRequest eventResult) {
        EventResult result = eventResultService.createEventResult(id, eventResult);
        return ResponseEntity.ok(EventResultMapper.mapToEventResultResponse(eventResultService.getEventResultsById(result.getId())));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Обновление результатов администратором")
    @PutMapping("/{id}/results")
    public ResponseEntity<?> updateEventResult(@PathVariable Long id, @RequestBody @Valid EventResultUpdateRequest eventResult) {
        EventResult result = eventResultService.updateEventResult(id, eventResult);
        return ResponseEntity.ok(EventResultMapper.mapToEventResultResponse(eventResultService.getEventResultsById(result.getId())));
    }
    @Operation(summary = "Вывод результатов мероприятия по идентификатору")
    @GetMapping("/{id}/results")
    public ResponseEntity<?> getEventResults(@PathVariable Long id) {
        return ResponseEntity.ok(EventResultMapper.mapToEventResultResponse(eventResultService.getEventResultsByEventId(id)));
    }

}
