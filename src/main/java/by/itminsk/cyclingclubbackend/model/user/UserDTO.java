package by.itminsk.cyclingclubbackend.model.user;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private Long id;

    private String email;

    private String password;
}
