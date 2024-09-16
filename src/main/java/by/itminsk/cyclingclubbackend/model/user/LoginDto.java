package by.itminsk.cyclingclubbackend.model.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {

    private String tel;

    private String password;

    private boolean rememberUser;
}
