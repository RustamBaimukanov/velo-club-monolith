package by.itminsk.cyclingclubbackend.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EventCalendarDto {

    private LocalDate date;

    private EventCalendarStatusEnum status;
}
