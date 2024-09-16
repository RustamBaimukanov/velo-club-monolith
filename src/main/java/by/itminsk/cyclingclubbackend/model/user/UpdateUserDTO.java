package by.itminsk.cyclingclubbackend.model.user;

import by.itminsk.cyclingclubbackend.model.role.RoleEnum;
import by.itminsk.cyclingclubbackend.model.social_network.SocialNetworkEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Map;

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
