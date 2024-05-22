package by.itminsk.cyclingclubbackend.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetDto {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    public UserGetDto(Long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
