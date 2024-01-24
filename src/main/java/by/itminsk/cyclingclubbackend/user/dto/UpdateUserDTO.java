package by.itminsk.cyclingclubbackend.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class UpdateUserDTO {

    String email;

    String tel;

    String firstName;

    String lastName;

    Date birth;

    String gender;

    MultipartFile userImg;

    String club;

    String region;

    String qualification;

    Double height;

    Double weight;

}
