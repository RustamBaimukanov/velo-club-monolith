package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.*;
import by.itminsk.cyclingclubbackend.r_city.City;
import by.itminsk.cyclingclubbackend.r_city.CityRepository;
import by.itminsk.cyclingclubbackend.r_city.CityService;
import by.itminsk.cyclingclubbackend.race.Race;
import by.itminsk.cyclingclubbackend.race.RaceRepository;
import by.itminsk.cyclingclubbackend.race.RaceService;
import by.itminsk.cyclingclubbackend.role.RoleRepository;
import by.itminsk.cyclingclubbackend.user.User;
import by.itminsk.cyclingclubbackend.user.UserRepository;
import by.itminsk.cyclingclubbackend.user.UserService;
import by.itminsk.cyclingclubbackend.user.dto.UserGetDto;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
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
    public void createEvent(EventPostDto eventPostDto) {
        validateCreateEventContent(eventPostDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Event event = Event.builder()
                .name(eventPostDto.getEventName())
                .note(eventPostDto.getEventDescription())
                .availableBirthDateFrom(eventPostDto.getBirthDateFrom())
                .availableBirthDateTo(eventPostDto.getBirthDateTo())
                .availableGender(eventPostDto.getGender())
                .race(Race.builder().id(eventPostDto.getBestRoute()).build())
                .availableRoles(roleRepository.findRolesByNameIn(eventPostDto.getParticipantsCategory().getRoleEnumSet()))
                .availableUsers(eventPostDto.getAddParticipants() != null ? eventPostDto.getAddParticipants()
                        .stream().map(participant -> User.builder().id(participant).build()).collect(Collectors.toSet()) : null)
                .city(City.builder().id(eventPostDto.getRegion()).build())
                .availableGender(eventPostDto.getGender())
                .createdUser(userService.findUserByPhoneNumber(currentPrincipalName))
                .date(new Date())
                .build();
        eventRepository.save(event);
    }

    @Override
    public void validateCreateEventContent(EventPostDto eventPostDto) {
        //TODO пока не ясно как обработать роли, найти решение при возникновении прецедента
        raceService.raceExistenceValidator(eventPostDto.getBestRoute());
        cityService.cityExistenceValidator(eventPostDto.getRegion());
        if (eventPostDto.getAddParticipants() != null)
            userService.userExistValidator(new HashSet<>(eventPostDto.getAddParticipants()));
    }

}
