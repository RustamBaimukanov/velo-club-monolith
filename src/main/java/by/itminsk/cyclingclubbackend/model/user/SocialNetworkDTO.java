package by.itminsk.cyclingclubbackend.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialNetworkDTO {

    private String name;

    private String link;

    private User user;
}
