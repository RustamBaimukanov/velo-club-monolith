package com.work.veloclub.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public record EventCalendarDto(LocalDate date, EventCalendarStatusEnum status){}
