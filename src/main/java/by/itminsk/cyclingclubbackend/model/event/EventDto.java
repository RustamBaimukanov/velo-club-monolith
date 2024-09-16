package by.itminsk.cyclingclubbackend.model.event;

import by.itminsk.cyclingclubbackend.model.role.RolesEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EventDto {

    private String eventName;

    //Время проведения мероприятия от
    private LocalDateTime startDate;

    //Время проведения мероприятия до
    private LocalDateTime endDate;

    private RolesEnum participantsCategory;
}
