package com.work.veloclub.model.event;

import com.work.veloclub.model.role.RolesEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventDto(String name,

                       //Время проведения мероприятия от
                       LocalDateTime startDate,

                       //Время проведения мероприятия до
                       LocalDateTime endDate,

                       RolesEnum participantsCategory) {


}
