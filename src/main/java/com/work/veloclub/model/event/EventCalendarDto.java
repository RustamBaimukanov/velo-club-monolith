package com.work.veloclub.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EventCalendarDto {

    private LocalDate date;

    private EventCalendarStatusEnum status;
}
