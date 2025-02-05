package com.work.veloclub.service.event_result;

import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.model.event_result.EventResultCreateRequest;
import com.work.veloclub.model.event_result.EventResultUpdateRequest;
import com.work.veloclub.model.user_profile.UserProfile;
import com.work.veloclub.repository.event.EventRepository;
import com.work.veloclub.repository.event_result.EventResultsRepository;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventResultServiceImpl implements EventResultService {

    private final EventResultsRepository eventResultsRepository;

    private final EventRepository eventRepository;

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
        EventResult result = new EventResult();
        result.setPlace(eventResult.place());
        result.setPoints(eventResult.points());
        result.setRaceTime(eventResult.raceTime());
        result.setStartOrder(eventResult.startOrder());
        result.setStartTime(eventResult.startTime());
        result.setEvent(eventRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Не найдено мероприятие по которому создаются результаты")));
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
