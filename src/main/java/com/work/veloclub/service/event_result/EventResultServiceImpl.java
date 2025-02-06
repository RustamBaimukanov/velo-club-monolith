package com.work.veloclub.service.event_result;

import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.model.event_result.EventResultCreateRequest;
import com.work.veloclub.model.event_result.EventResultUpdateRequest;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.user_profile.UserProfile;
import com.work.veloclub.repository.event.EventRepository;
import com.work.veloclub.repository.event_result.EventResultsRepository;
import com.work.veloclub.repository.user.UserRepository;
import com.work.veloclub.repository.user_profile.UserProfileRepository;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.UnacceptableDataException;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventResultServiceImpl implements EventResultService {

    private final EventResultsRepository eventResultsRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    @Override
    public EventResult getEventResultsById(Long id) {
        return eventResultsRepository.findById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.EventResultErrors.NOT_FOUND));
    }

    @Override
    public List<EventResult> getEventResultsByEventId(Long id) {
        return eventResultsRepository.findAllByEventId(id);
    }

    @Override
    @Transactional
    public EventResult createEventResult(Long id, EventResultCreateRequest eventResult) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Не найдено мероприятие по которому создаются результаты"));
        Set<RoleEnum> roleEnumSet = event.getAvailableRoles().stream().map(Role::getName).collect(Collectors.toSet());
        RoleEnum userRole = userRepository.findUserWithRoleById(eventResult.userId()).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND)).getRole().getName();
        List<Long> additionalUsers = event.getAdditionalUsers().stream().map(userProfile -> userProfile.getUserProfile().getId()).toList();
        if (!roleEnumSet.contains(userRole)){
            if (!additionalUsers.isEmpty() && !additionalUsers.contains(eventResult.userId())) throw new UnacceptableDataException("Пользователь не может участвовать");
        }
        EventResult result = new EventResult();
        result.setPlace(eventResult.place());
        result.setPoints(eventResult.points());
        result.setRaceTime(eventResult.raceTime());
        result.setStartOrder(eventResult.startOrder());
        result.setStartTime(eventResult.startTime());
        result.setEvent(event);
        result.setUserProfile(UserProfile.builder().id(eventResult.userId()).build());
        eventResultsRepository.save(result);
        return result;
    }

    @Override
    @Transactional
    public EventResult updateEventResult(Long id, EventResultUpdateRequest eventResult) {
        EventResult result = eventResultsRepository.findById(eventResult.id()).orElseThrow(() -> new ObjectNotFound(ErrorMessages.EventResultErrors.NOT_FOUND));
        result.setPlace(eventResult.place());
        result.setPoints(eventResult.points());
        result.setRaceTime(eventResult.raceTime());
        result.setStartOrder(eventResult.startOrder());
        result.setStartTime(eventResult.startTime());
        result.setEvent(eventRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Не найдено мероприятие по которому создаются результаты")));
        result.setUserProfile(UserProfile.builder().id(eventResult.userId()).build());
        return result;
    }
}
