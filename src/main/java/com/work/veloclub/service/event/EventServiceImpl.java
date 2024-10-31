package com.work.veloclub.service.event;

import com.work.veloclub.model.event.*;
import com.work.veloclub.model.event_result.EventBlockDTO;
import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.repository.event_result.EventResultsRepository;
import com.work.veloclub.model.event.*;
import com.work.veloclub.model.city.City;
import com.work.veloclub.repository.city.CityRepository;
import com.work.veloclub.service.city.CityService;
import com.work.veloclub.model.race.Race;
import com.work.veloclub.service.race.RaceService;
import com.work.veloclub.repository.event.EventRepository;
import com.work.veloclub.repository.role.RoleRepository;
import com.work.veloclub.model.user.User;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
@AllArgsConstructor
@Validated
@Slf4j
public class EventServiceImpl implements EventService {

    private final RaceService raceService;

    private final CityService cityService;

    private final UserService userService;

    private final EventRepository eventRepository;

    private final EventResultsRepository eventResultsRepository;

    private final RoleRepository roleRepository;


    private final CityRepository cityRepository;

    @Override
    public EventBlockDTO getEvent(Long id) {
        Map<String, Integer> rating = new HashMap<>();
        for (EventResult result :
                eventResultsRepository.findAllByEventId(id)) {
            rating.put(result.getUser().getFirstName() + " " + result.getUser().getLastName(), result.getPlace());
        }
        EventBlockDTO eventBlockDTO = eventRepository.findEventById(id);
        eventBlockDTO.setRating(rating);
        return eventBlockDTO;
    }

    @Override
    public List<Event> getEventsByDay(LocalDate date) {
        return eventRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(date.atTime(0,0), date.atTime(0,0));

    }

    @Override
    public List<Event> getEventsByMonth(LocalDate date) {
        return eventRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(date.with(lastDayOfMonth()).atTime(0,0), date.with(firstDayOfMonth()).atTime(0,0));
    }

    @Override
    //TODO переписать преобразователь в другом методе если текущий вариант усложнится.
    public List<EventCalendarDto> getEventCalendar(LocalDate date) {
        YearMonth ym = YearMonth.of(date.getYear(), date.getMonth());
        LocalDate firstOfMonth = ym.atDay(1);
        LocalDate firstOfFollowingMonth = ym.plusMonths(1).atDay(1);
        List<EventDto> events = getEventsByMonth(date).stream().map(event -> EventDto.builder()
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build()).toList();
        List<EventCalendarDto> eventCalendarList = new ArrayList<>();
        firstOfMonth.datesUntil(firstOfFollowingMonth).forEach(calendarDate -> {
                    LocalDateTime calendarDateTime = calendarDate.atTime(0, 0);
                    if (events.stream()
                            .anyMatch(event -> (event.getStartDate().isBefore(calendarDateTime) || event.getStartDate().isEqual(calendarDateTime))
                                    && (event.getEndDate().isAfter(calendarDateTime) || event.getEndDate().isEqual(calendarDateTime))))
                        eventCalendarList.add(new EventCalendarDto(calendarDate, EventCalendarStatusEnum.EXIST));
                    else {
                        eventCalendarList.add(new EventCalendarDto(calendarDate, EventCalendarStatusEnum.NOT_EXIST));
                    }
                }
        );
        return eventCalendarList;
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Мероприятие не найдено."));
    }

    @Override
    public void createEvent(Event event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        event.setCreatedUser(userService.findUserByPhoneNumber(currentPrincipalName));
        event.setAvailableRoles(roleRepository.findRolesByNameIn(event.getParticipantsCategory().getRoleEnumSet()));
        eventRepository.save(event);
    }

    @Override
    public void updateEvent(EventPostDto eventPostDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Event event = getById(eventPostDto.getId()).toBuilder()
                .name(eventPostDto.getEventName())
                .note(eventPostDto.getEventDescription())
                .availableBirthDateFrom(eventPostDto.getBirthDateFrom())
                .availableBirthDateTo(eventPostDto.getBirthDateTo())
                .startDate(eventPostDto.getStartDate())
                .endDate(eventPostDto.getEndDate())
                .availableGender(eventPostDto.getGender())
                .race(Race.builder().id(eventPostDto.getBestRoute()).build())
                .availableRoles(roleRepository.findRolesByNameIn(eventPostDto.getParticipantsCategory().getRoleEnumSet()))
                .availableUsers(eventPostDto.getAddParticipants() != null ? eventPostDto.getAddParticipants()
                        .stream().map(participant -> User.builder().id(participant).build()).collect(Collectors.toSet()) : null)
                .city(City.builder().id(eventPostDto.getRegion()).build())
                .availableGender(eventPostDto.getGender())
                .date(new Date())
                .build();
        userService.sameUserValidator(event.getCreatedUser().getId(), currentPrincipalName);

        eventRepository.save(event);
    }

    @Override
    public void validateCreateEventContent(EventPostDto eventPostDto) {
        //TODO пока не ясно как обработать роли, найти решение при возникновении прецедента
        raceService.raceExistenceValidator(eventPostDto.getBestRoute());
        cityService.cityExistenceValidator(eventPostDto.getRegion());
        if (eventPostDto.getAddParticipants() != null && !eventPostDto.getAddParticipants().isEmpty())
            userService.userExistValidator(new HashSet<>(eventPostDto.getAddParticipants()));
    }

    @Override
    public void eventExistenceValidator(Long id) {
        if (!eventRepository.existsById(id)) throw new ObjectNotFound("Мероприятие не найдено.");
    }

}
