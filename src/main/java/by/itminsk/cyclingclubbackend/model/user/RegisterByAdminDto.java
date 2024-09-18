package by.itminsk.cyclingclubbackend.model.user;

import by.itminsk.cyclingclubbackend.model.role.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

public record RegisterByAdminDto
        (String firstName,
         String lastName,
         String tel,
         String email,
         String password,
         Double height,
         Double weight,
         LocalDate birth,
         GenderEnum gender,
         Long region,
         Long club,
         MultipartFile userImg,
         RoleEnum qualification){
}
