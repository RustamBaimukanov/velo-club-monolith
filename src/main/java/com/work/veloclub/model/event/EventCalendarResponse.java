package com.work.veloclub.model.event;


import com.work.veloclub.model.race.RaceResponse;
import com.work.veloclub.model.role.RolesEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventCalendarResponse(
        Long id,
        String name,
        //Время проведения мероприятия от
        LocalDateTime startDate,
        //Время проведения мероприятия до
        LocalDateTime endDate,
        RolesEnum participantsCategory,
        RaceResponse race) {
}
