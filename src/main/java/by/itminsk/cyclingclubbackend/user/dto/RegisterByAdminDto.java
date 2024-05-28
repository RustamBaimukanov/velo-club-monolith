package by.itminsk.cyclingclubbackend.user.dto;

import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RegisterByAdminDto implements Serializable {

    String firstName ;
    String lastName ;
    String tel;
    String email;
    String password;
    Double height;
    Double weight;
    String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    Date birth;
    GenderEnum gender;
    String region;
    String club;
    MultipartFile userImg;
    RoleEnum qualification;
}
