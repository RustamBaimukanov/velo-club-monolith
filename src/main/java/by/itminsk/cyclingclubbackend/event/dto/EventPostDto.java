package by.itminsk.cyclingclubbackend.event.dto;

import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EventPostDto {

    private String eventName;

    private String eventDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthDateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthDateTo;

    private String participantsCategory;

    private String gender;

    private String region;




}
