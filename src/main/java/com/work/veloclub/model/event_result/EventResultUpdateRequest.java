package com.work.veloclub.model.event_result;

public record EventResultUpdateRequest(
        Long id, Integer place, Integer points, Double raceTime, Long userId

) {
}
