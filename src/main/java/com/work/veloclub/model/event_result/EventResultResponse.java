package com.work.veloclub.model.event_result;


import com.work.veloclub.model.user.dto.UserShortResponse;

public record EventResultResponse(
        Long id, Integer place, Integer points, Double raceTime, UserShortResponse user
) {
}
