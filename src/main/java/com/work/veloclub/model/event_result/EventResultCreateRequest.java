package com.work.veloclub.model.event_result;

import java.time.LocalDateTime;

public record EventResultCreateRequest(
        Integer place, Integer points, Double raceTime,
        Integer startOrder,

        LocalDateTime startTime, Long userId

) {
}
