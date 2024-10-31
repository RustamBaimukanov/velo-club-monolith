package by.itminsk.cyclingclubbackend.model.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String phoneNumber;

    private String password;

}
