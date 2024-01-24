package by.itminsk.cyclingclubbackend.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {

    private String tel;

    private String password;

    private boolean rememberUser;
}
