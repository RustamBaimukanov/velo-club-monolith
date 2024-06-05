package by.itminsk.cyclingclubbackend.user.dto;

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
    GenderEnum gender;
    Long city;
    Map<String, String> socialNetwork = new HashMap<>();

}
