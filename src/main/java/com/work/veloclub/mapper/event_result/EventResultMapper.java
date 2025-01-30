package com.work.veloclub.mapper.event_result;

import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.model.event_result.EventResultResponse;
import com.work.veloclub.model.user.dto.UserShortResponse;

import java.util.List;

public class EventResultMapper {

    public static EventResultResponse mapToEventResultResponse(EventResult eventResult){
        String team = null;
        if (eventResult.getUserProfile().getTeam() != null){
            team = eventResult.getUserProfile().getTeam().getName();
        }
        return new EventResultResponse(
                eventResult.getId(),
                eventResult.getPlace(),
                eventResult.getPoints(),
                eventResult.getRaceTime(),
                new UserShortResponse(
                        eventResult.getUserProfile().getId(),
                        eventResult.getUserProfile().getFirstName(),
                        eventResult.getUserProfile().getLastName(),
                        eventResult.getUserProfile().getSurname(),
                        eventResult.getUserProfile().getBirthDate(),
                        eventResult.getUserProfile().getGender(),
                        team,
                        eventResult.getUserProfile().getCity().getName()
                ));
    }

    public static List<EventResultResponse> mapToEventResultResponse(List<EventResult> eventResult){
        return eventResult.stream().map(EventResultMapper::mapToEventResultResponse).toList();
    }
}
