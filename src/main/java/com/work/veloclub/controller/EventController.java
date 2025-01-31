package com.work.veloclub.controller;


import com.work.veloclub.mapper.event.EventMapper;
import com.work.veloclub.mapper.event_result.EventResultMapper;
import com.work.veloclub.mapper.race.RaceMapper;
import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event.EventCalendarResponse;
import com.work.veloclub.model.event.EventCreateDTO;
import com.work.veloclub.model.event.EventGetFilter;
import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.model.event_result.EventResultCreateRequest;
import com.work.veloclub.model.event_result.EventResultUpdateRequest;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.service.category.CategoryService;
import com.work.veloclub.service.event.EventService;
import com.work.veloclub.service.event_result.EventResultService;
import com.work.veloclub.util.exception_handler.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    private final CategoryService categoryService;

    @Operation(
            summary = "Script",
            description = "Не использовать(наполнение данных)."
    )
    @PostMapping("/script")
    public ResponseEntity<?> ta() {
        eventService.script();
        return ResponseEntity.ok(null);
    }

    @Operation(
            summary = "Добавление мероприятия",
            description = "API добавления мероприятия(будет дополнено, после обсуждении).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь создал мероприятие"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping
    public ResponseEntity<?> addEvent(@RequestBody @Valid EventCreateDTO eventDto) {
        categoryService.categoryExistenceValidator(eventDto.categoryId());
        Event event = eventService.createEvent(eventDto);
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
            description = "API редактирования мероприятия",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь обновил мероприятие"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody @Valid EventCreateDTO eventDto) {
        categoryService.categoryExistenceValidator(eventDto.categoryId());
        Event event = eventService.updateEvent(id, eventDto);
        return ResponseEntity.ok(EventMapper.mapToEventDto(event));
    }

    @Operation(
            summary = "Получение списка мероприятии",
            description = "Получение списка мероприятии с помощью фильтра",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил список мероприятии"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @GetMapping
    public ResponseEntity<?> getEvent(EventGetFilter filter) {
        List<Event> events = eventService.getEventsByFilter(filter);
        return ResponseEntity.ok(EventMapper.mapToEventListDto(events));
    }

    @Operation(
            summary = "Получение мероприятии по текущей дате",
            description = "",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил список мероприятии по определенной дате вместе с маршрутом"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @GetMapping("/date")
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
            description = "",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил список мероприятии по определенной дате вместе с маршрутом"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @GetMapping("/calendar")
    public ResponseEntity<?> getCalendar(@RequestParam
                                         LocalDate date) {
        return ResponseEntity.ok(eventService.getEventCalendar(date));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Создание результатов администратором",
            description = "Пока не понятно как на самом деле будут сохраняться мероприятия, мб и списком",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь создал результат мероприятия"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })
    @PostMapping("/{id}/results")
    public ResponseEntity<?> addEventResult(@PathVariable Long id, @RequestBody @Valid EventResultCreateRequest eventResult) {
        EventResult result = eventResultService.createEventResult(id, eventResult);
        return ResponseEntity.ok(EventResultMapper.mapToEventResultResponse(eventResultService.getEventResultsById(result.getId())));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Обновление результатов администратором",
            description = "Пока не понятно как на самом деле будут обновляться мероприятия",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь обновил результат мероприятия(будет ли нужен вообще такой функционал?)"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })    @PutMapping("/{id}/results")
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
