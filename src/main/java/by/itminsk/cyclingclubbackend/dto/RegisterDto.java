package by.itminsk.cyclingclubbackend.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto implements Serializable {

    String firstName ;
    String lastName ;
    String tel;
    String email;
    String password ;
    String confirmPassword;
    Boolean acceptTerms;
    Date birth;
    String gender;
    String city;
    Map<String, String> socialNetwork = new HashMap<>();

}
