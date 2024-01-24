package by.itminsk.cyclingclubbackend.user.dto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
    Date birth;
    String gender;
    String region;
    String club;
    MultipartFile userImg;
    String qualification;
}
