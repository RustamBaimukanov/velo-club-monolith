package by.itminsk.cyclingclubbackend.user.dto;

import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import by.itminsk.cyclingclubbackend.social_network.SocialNetworkEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDTO {

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birth;

    private GenderEnum gender;

    private MultipartFile userImg;

    private Long club;

    private Long region;

    private RoleEnum qualification;

    private Double height;

    private Double weight;

    private ImageStateEnum imageStatus;

    private Map<SocialNetworkEnum, String> socialNetworks;

}
