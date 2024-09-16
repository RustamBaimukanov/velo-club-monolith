package by.itminsk.cyclingclubbackend.model.user;

import by.itminsk.cyclingclubbackend.model.role.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RegisterByAdminDto implements Serializable {

    String firstName;
    String lastName;
    String tel;
    String email;
    String password;
    Double height;
    Double weight;
    String address;
    LocalDate birth;
    GenderEnum gender;
    Long region;
    Long club;
    MultipartFile userImg;
    RoleEnum qualification;
}
