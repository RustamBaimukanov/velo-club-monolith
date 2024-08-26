package by.itminsk.cyclingclubbackend.event.dto;

import by.itminsk.cyclingclubbackend.race.dto.RaceDto;
import by.itminsk.cyclingclubbackend.role.dto.RolesEnum;
import by.itminsk.cyclingclubbackend.user.dto.GenderEnum;
import by.itminsk.cyclingclubbackend.user.dto.UserGetDto;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventPostDto {

    private Long id;

    @NotBlank(message = "Название мероприятия не может быть пустым")
    @Size(max = 255,message = "Название мероприятия не может превышать 255 символов")
    private String eventName;

    @Size(max = 4000, message = "Описание мероприятия не может превышать N символов")
    private String eventDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Past(message = "Недопустимое возрастное ограничение")
    @NotNull(message = "Не указано возрастное ограничение от")
    private Date birthDateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Не указано возрастное ограничение до")
    private Date birthDateTo;

    //TODO пока нет в форме, не валидируется
    private LocalDateTime startDate;

    //TODO пока нет в форме, не валидируется
    private LocalDateTime endDate;

    @NotNull(message = "Не выбрана категория участников")
    private RolesEnum participantsCategory;

    private List<Long> addParticipants;

    @NotNull(message = "Не выбран маршрут")
    private Long bestRoute;

    @NotNull(message = "Не выбрано ограничение по полу")
    private GenderEnum gender;

    @NotNull(message = "Не указано местоположение для мероприятия")
    private Long region;




}
