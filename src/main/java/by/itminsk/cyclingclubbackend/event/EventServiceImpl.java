package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.EventBlockDTO;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class EventServiceImpl implements EventService{

    private final RaceService raceService;

    private final CityService cityService;

    private final UserService userService;

    private final EventRepository eventRepository;

    private final EventResultsRepository eventResultsRepository;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final CityRepository cityRepository;

    @Override
    public EventBlockDTO getEvent(Long id) {
        Map<String, Integer> rating = new HashMap<>();
        for (EventResult result:
        eventResultsRepository.findAllByEventId(id)) {
            rating.put(result.getUser().getFirstName() + " " + result.getUser().getLastName(), result.getPlace());
        }
        EventBlockDTO eventBlockDTO = eventRepository.findEventById(id);
        eventBlockDTO.setRating(rating);
        return eventBlockDTO;
    }

    @Override
    public void createEvent(EventPostDto eventPostDto) {
        validateCreateEventContent(eventPostDto);
//raceRepository.findById(eventPostDto.getBestRoute().getId()).orElseThrow(() -> new ObjectNotFound("Маршрут не найден."))
        Event event = Event.builder()
                .name(eventPostDto.getEventName())
                .note(eventPostDto.getEventDescription())
                .availableBirthDateFrom(eventPostDto.getBirthDateFrom())
                .availableBirthDateTo(eventPostDto.getBirthDateTo())
                .availableGender(eventPostDto.getGender())
                .race(Race.builder().id(eventPostDto.getBestRoute().getId()).build())
                .availableRoles(roleRepository.findRolesByNameIn(eventPostDto.getParticipantsCategory().getRoleEnumSet()))
                .availableUsers(eventPostDto.getAddParticipants().stream().map(participant -> User.builder().id(participant.getId()).build()).collect(Collectors.toSet()))
                .city(City.builder().id(eventPostDto.getRegionId()).build())
                .availableGender(eventPostDto.getGender())
                .build();
        eventRepository.save(event);
    }

    @Override
    public void validateCreateEventContent(EventPostDto eventPostDto) {
        //TODO пока не ясно как обработать роли, найти решение при возникновении прецедента
        raceService.raceExistenceValidator(eventPostDto.getBestRoute().getId());
        cityService.cityExistenceValidator(eventPostDto.getRegionId());
        userService.userExistValidator(eventPostDto.getAddParticipants().stream().map(UserGetDto::getId).collect(Collectors.toSet()));
    }

}
