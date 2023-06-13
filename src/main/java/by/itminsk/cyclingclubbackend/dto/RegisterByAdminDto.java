package by.itminsk.cyclingclubbackend.dto;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterByAdminDto implements Serializable {

    String firstName ;
    String lastName ;
    String phoneNumber;
    String email;
    String password;
    Date birthDate;
    String sex;
    Double height;
    Date weight;
    String address;
    Long teamId; //Предполагается выбор команды из списка
}
