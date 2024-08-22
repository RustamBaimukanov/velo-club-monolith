package by.itminsk.cyclingclubbackend.event.dto;

import by.itminsk.cyclingclubbackend.role.dto.RolesEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
