package by.itminsk.cyclingclubbackend.event.dto;

import by.itminsk.cyclingclubbackend.role.dto.RolesEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EventDto {

    private String eventName;

    //Время проведения мероприятия от
    private Date startDate;

    //Время проведения мероприятия до
    private Date endDate;

    private RolesEnum participantsCategory;
}
