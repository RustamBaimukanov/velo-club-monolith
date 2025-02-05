package com.work.veloclub.model.event_result;


import com.work.veloclub.model.user.dto.UserShortResponse;

import java.time.LocalDateTime;

public record EventStartProtocolResponse(
        Long id, Integer startOrder, LocalDateTime startTime, UserShortResponse user
) {
}
