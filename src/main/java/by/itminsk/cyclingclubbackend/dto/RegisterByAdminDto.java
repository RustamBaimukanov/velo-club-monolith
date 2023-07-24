package by.itminsk.cyclingclubbackend.dto;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RegisterByAdminDto implements Serializable {

    String firstName ;
    String lastName ;
    String tel;
    String email;
    String password;
    Double height;
    Date weight;
    String address;
    Date birth;
    String gender;
    String region;
    String club;
    MultipartFile userImg;
}
