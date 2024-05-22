package by.itminsk.cyclingclubbackend.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    Date birth;

    String gender;

    MultipartFile userImg;

    String club;

    String region;

    String qualification;

    Double height;

    Double weight;

}
