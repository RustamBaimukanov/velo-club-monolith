package com.work.veloclub.model.event_result;

public record EventResultCreateRequest(
        Integer place, Integer points, Double raceTime, Long userId

) {
}
