package com.work.veloclub.service.event;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.event.*;
import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.race.Race;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user_profile.UserProfile;
import com.work.veloclub.repository.city.CityRepository;
import com.work.veloclub.repository.event.CategoryRepository;
import com.work.veloclub.repository.event.EventRepository;
import com.work.veloclub.repository.event.EventSpecification;
import com.work.veloclub.repository.role.RoleRepository;
import com.work.veloclub.service.city.CityService;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
@AllArgsConstructor
@Validated
@Slf4j
public class EventServiceImpl implements EventService {

    private final CityService cityService;

    private final UserService userService;

    private final EventRepository eventRepository;

    private final RoleRepository roleRepository;

    private final CategoryRepository categoryRepository;


    private final CityRepository cityRepository;

    @Override
    @Transactional
    public Event createEvent(EventCreateDTO eventCreateDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Event event = new Event();
        event.setName(eventCreateDTO.name());
        event.setDescription(eventCreateDTO.description());
        event.setBirthDateFrom(eventCreateDTO.birthDateFrom());
        event.setBirthDateTo(eventCreateDTO.birthDateTo());
        event.setStartDate(eventCreateDTO.startDate());
        event.setEndDate(eventCreateDTO.endDate());
        event.setRace(Race.builder().id(eventCreateDTO.bestRoute()).build());
        event.setCity(City.builder().id(eventCreateDTO.city()).build());
        event.setAvailableGender(eventCreateDTO.gender());
        event.setCreatedUser(user.getUserProfile());
        event.setCategory(Category.builder().id(eventCreateDTO.categoryId()).build());

        Set<RoleEnum> roleEnumSet = new HashSet<>();
        switch (eventCreateDTO.participantsCategory()) {
            case ANY -> {
                roleEnumSet.add(RoleEnum.SPORTSMAN);
                roleEnumSet.add(RoleEnum.DABBLER);
            }
            case SPORTSMAN -> roleEnumSet.add(RoleEnum.SPORTSMAN);
            case DABBLER -> roleEnumSet.add(RoleEnum.DABBLER);
        }

        event.setAvailableRoles(roleRepository.findRolesByNameIn(roleEnumSet));

        if (eventCreateDTO.participantsCategory() != RolesEnum.ANY && eventCreateDTO.participants() != null && !eventCreateDTO.participants().isEmpty()) {
            for (Long userProfileId :
                    eventCreateDTO.participants()) {
                event.addProfile(UserProfile.builder().id(userProfileId).build());
            }
        }
        eventRepository.save(event);
        return event;
    }

    @Override
    public List<Event> createEventGenerate(List<EventCreateDTO> eventCreateDTOList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Event> events = eventCreateDTOList.stream()
                .map(eventCreateDTO -> {
                    Event event = new Event();
                    event.setName(eventCreateDTO.name());
                    event.setDescription(eventCreateDTO.description());
                    event.setBirthDateFrom(eventCreateDTO.birthDateFrom());
                    event.setBirthDateTo(eventCreateDTO.birthDateTo());
                    event.setStartDate(eventCreateDTO.startDate());
                    event.setEndDate(eventCreateDTO.endDate());
                    event.setRace(Race.builder().id(eventCreateDTO.bestRoute()).build());
                    event.setCity(City.builder().id(eventCreateDTO.city()).build());
                    event.setAvailableGender(eventCreateDTO.gender());
                    event.setCreatedUser(user.getUserProfile());

                    Set<RoleEnum> roleEnumSet = new HashSet<>();
                    switch (eventCreateDTO.participantsCategory()) {
                        case ANY -> {
                            roleEnumSet.add(RoleEnum.SPORTSMAN);
                            roleEnumSet.add(RoleEnum.DABBLER);
                        }
                        case SPORTSMAN -> roleEnumSet.add(RoleEnum.SPORTSMAN);
                        case DABBLER -> roleEnumSet.add(RoleEnum.DABBLER);
                    }

                    event.setAvailableRoles(roleRepository.findRolesByNameIn(roleEnumSet));

                    if (eventCreateDTO.participantsCategory() != RolesEnum.ANY && eventCreateDTO.participants() != null && !eventCreateDTO.participants().isEmpty()) {
                        for (Long userProfileId :
                                eventCreateDTO.participants()) {
                            event.addProfile(UserProfile.builder().id(userProfileId).build());
                        }
                    }
                    return event;
                }).toList();

        eventRepository.saveAll(events);
        return events;
    }

    @Override
    @Transactional
    public Event updateEvent(Long id, EventCreateDTO eventCreateDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Event event = eventRepository.findById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.EventErrors.NOT_FOUND));
        event.setName(eventCreateDTO.name());
        event.setDescription(eventCreateDTO.description());
        event.setBirthDateFrom(eventCreateDTO.birthDateFrom());
        event.setBirthDateTo(eventCreateDTO.birthDateTo());
        event.setStartDate(eventCreateDTO.startDate());
        event.setEndDate(eventCreateDTO.endDate());
        event.setRace(Race.builder().id(eventCreateDTO.bestRoute()).build());
        event.setCity(City.builder().id(eventCreateDTO.city()).build());
        event.setAvailableGender(eventCreateDTO.gender());
        event.setCreatedUser(user.getUserProfile());
        event.setCategory(Category.builder().id(eventCreateDTO.categoryId()).build());

        Set<RoleEnum> roleEnumSet = new HashSet<>();
        switch (eventCreateDTO.participantsCategory()) {
            case ANY -> {
                roleEnumSet.add(RoleEnum.SPORTSMAN);
                roleEnumSet.add(RoleEnum.DABBLER);
            }
            case SPORTSMAN -> roleEnumSet.add(RoleEnum.SPORTSMAN);
            case DABBLER -> roleEnumSet.add(RoleEnum.DABBLER);
        }

        event.setAvailableRoles(roleRepository.findRolesByNameIn(roleEnumSet));

        if (eventCreateDTO.participantsCategory() != RolesEnum.ANY && eventCreateDTO.participants() != null && !eventCreateDTO.participants().isEmpty()) {
            for (Long userProfileId :
                    eventCreateDTO.participants()) {
                event.addProfile(UserProfile.builder().id(userProfileId).build());
            }
        }
        return event;
    }

//    @Override
//    public EventBlockDTO getEvent(Long id) {
//        Map<String, Integer> rating = new HashMap<>();
//        for (EventResult result :
//                eventResultsRepository.findAllByEventId(id)) {
//            rating.put(result.getUser().getFirstName() + " " + result.getUser().getLastName(), result.getPlace());
//        }
//        EventBlockDTO eventBlockDTO = eventRepository.findEventById(id);
//        eventBlockDTO.setRating(rating);
//        return eventBlockDTO;
//    }

    @Override
    public List<Event> getEventsByDay(Integer size, LocalDate date) {
        if (size == null){
            return eventRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(date.atTime(0, 0), date.atTime(0, 0));
        }
        return eventRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(date.atTime(0, 0), date.atTime(0, 0)).stream().limit(size).toList();

    }

    @Override
    public List<Event> getEventsByMonth(LocalDate date) {
        return eventRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(date.with(lastDayOfMonth()).atTime(0, 0), date.with(firstDayOfMonth()).atTime(0, 0));
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
                            .anyMatch(event -> (event.startDate().isBefore(calendarDateTime) || event.startDate().isEqual(calendarDateTime))
                                    && (event.endDate().isAfter(calendarDateTime) || event.endDate().isEqual(calendarDateTime))))
                        eventCalendarList.add(new EventCalendarDto(calendarDate, EventCalendarStatusEnum.EXIST));
                    else {
                        eventCalendarList.add(new EventCalendarDto(calendarDate, EventCalendarStatusEnum.NOT_EXIST));
                    }
                }
        );
        return eventCalendarList;
    }

    @Override
    public List<Event> getEventsByFilter(EventGetFilter filter) {
        return Event.filterByAgeRange(eventRepository.findAll(EventSpecification.filterByEventRequest(filter), Sort.by("startDate").descending()), filter.birthDateFrom(), filter.birthDateTo());
    }

    @Override
    public List<Event> getEventsByUserIdAndYear(Long id, Integer year) {
        User user = userService.getUserWithRoleById(id);
        return eventRepository.findAll(EventSpecification.filterByUserIdAndYearAndRole(id, year, user.getRole().getName()), Sort.by("startDate").descending());
    }

    @Override
    public List<Event> getEventsByUserIdAndYear(Long id, Integer year, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("startDate").descending());
        User user = userService.getUserWithRoleById(id);

        return eventRepository.findAll(EventSpecification.filterByUserIdAndYearAndRole(id, year, user.getRole().getName()), pageable).getContent();
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Мероприятие не найдено."));
    }


    @Override
    public void validateCreateEventContent(EventCreateDTO eventPostDto) {
        //TODO пока не ясно как обработать роли, найти решение при возникновении прецедента
//        raceService.raceExistenceValidator(eventPostDto.getBestRoute());
//        cityService.cityExistenceValidator(eventPostDto.getRegion());
//        if (eventPostDto.getAddParticipants() != null && !eventPostDto.getAddParticipants().isEmpty())
//            userService.userExistValidator(new HashSet<>(eventPostDto.getAddParticipants()));
    }

    @Override
    public void eventExistenceValidator(Long id) {
        if (id != null && !eventRepository.existsById(id)) throw new ObjectNotFound("Мероприятие не найдено.");
    }

    @Override
    public void script() {
        List<Event> events = eventRepository.findAll();
        events.stream().forEach(event -> event.setCategory(categoryRepository.findById(7L).get()));
    }

}
