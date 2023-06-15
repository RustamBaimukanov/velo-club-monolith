package by.itminsk.cyclingclubbackend.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto implements Serializable {

    String firstName ;
    String lastName ;
    String phoneNumber;
    String email;
    String password ;
    String confirmPassword;
}
