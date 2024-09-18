package by.itminsk.cyclingclubbackend.model.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto{

    private String firstName;
    private String lastName;
    private String tel;
    private String email;
    private String password;
    private String confirmPassword;
    private Boolean acceptTerms;
    private LocalDate birth;
    private GenderEnum gender;
    private Long city;
    private Map<String, String> socialNetwork = new HashMap<>();

}
